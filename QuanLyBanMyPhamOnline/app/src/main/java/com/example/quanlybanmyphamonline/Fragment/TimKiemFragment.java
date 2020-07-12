package com.example.quanlybanmyphamonline.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlybanmyphamonline.Adapter.ListViewTimKiemAdapter;
import com.example.quanlybanmyphamonline.Class.HorizontalModel;
import com.example.quanlybanmyphamonline.Class.LichSuSearch;
import com.example.quanlybanmyphamonline.Class.Server;
import com.example.quanlybanmyphamonline.R;
import com.example.quanlybanmyphamonline.Class.SendData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimKiemFragment extends Fragment {
    private SearchView searchView ;
    RelativeLayout container,containerTimKiem;
    TagGroup mTagGroup;
    ListView listViewTimKiem,listViewLichSuTimKiem;
    ListViewTimKiemAdapter adapter,adapterlichsu;
    HorizontalModel horizontalModel;
    ArrayList<HorizontalModel> arr,arrLichSu;

    SendData listener;

    public TimKiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tim_kiem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa();
        adapterlichsu = new ListViewTimKiemAdapter(getContext(),R.layout.item_timkiem,LichSuSearch.getArrLichSu());
        listViewLichSuTimKiem.setAdapter(adapterlichsu);
        SearchView();
    }

    public void AnhXa()
    {
        searchView = getView().findViewById(R.id.search_viewtimkiem);
        mTagGroup =  getView().findViewById(R.id.tag_group);
        listViewLichSuTimKiem=getView().findViewById(R.id.ListViewLichSuTimKiem);
        listViewTimKiem=getView().findViewById(R.id.ListViewTimKiem);
        container=getView().findViewById(R.id.container_lstviewTimKiem);
        containerTimKiem=getView().findViewById(R.id.containerTimKiem);
    }

    public void SearchView()
    {

        TaoDuLieu();
        searchView.setIconified(false);
        searchView.setFocusable(true);
        searchView.setIconifiedByDefault(true);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if(searchView.getQuery().length() != 0  )
                {
                    adapter.filter(newText.trim());
                    arr=adapter.getArrayList();
                    adapter.notifyDataSetChanged();
                    container.setVisibility(View.VISIBLE);
                    containerTimKiem.setVisibility(View.GONE);
                }
                else
                {
                    container.setVisibility(View.GONE);
                    containerTimKiem.setVisibility(View.VISIBLE);
                }
                return false;
            }

        })
        ;mTagGroup.setTags(new String[]{"Son", "Mascara", "Nước tẩy trang","Nước rửa mặt","Mặt nạ dưỡng ẩm"});
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView); } });



        listViewTimKiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int temp = 0;
                if(LichSuSearch.getArrLichSu().size() != 0)
                {
                    for(int i = 0 ; i < LichSuSearch.getArrLichSu().size() ; i++)
                    {
                        if(LichSuSearch.getArrLichSu().get(i).getTen()== arr.get(position).getTen())
                        {
                            temp++;
                            break;
                        }
                    }
                }
                if(temp == 0){
                    LichSuSearch.arrLichSu.add(arr.get(position));
                    adapterlichsu.notifyDataSetChanged();
                }

                listener.sendData(arr.get(position).getMaloaisp());
                DanhMucFragment danhMucFragment=new DanhMucFragment();
                loadFragment(danhMucFragment);

            }
        });

    }
    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void TaoDuLieu()
    {
        arr = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanALLSP, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
//                        Toast.makeText(getContext(), jsonObject.getString("tensp"), Toast.LENGTH_SHORT).show();
                        int masanpham = jsonObject.getInt("masp");
                        String tensp = jsonObject.getString("tensp");

                        String mota = jsonObject.getString("mota");
                        int gia = jsonObject.getInt("gia");
                        int maloaisanpham = jsonObject.getInt("MaLoaiSanPham");
                        String hinh = jsonObject.getString("hinh");


                        HorizontalModel horizontalModel = new HorizontalModel();
                        horizontalModel.setGia("Giá : " + gia);
                        horizontalModel.setMota(mota);
                        horizontalModel.setTen(tensp);
                        horizontalModel.setHinh(hinh);
                        horizontalModel.setMaloaisp(maloaisanpham);
                        horizontalModel.setMasp(masanpham);

                        arr.add(horizontalModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                adapter = new ListViewTimKiemAdapter(getContext(),R.layout.item_timkiem,arr);
                listViewTimKiem.setAdapter(adapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData){
            listener= (SendData) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onViewSelected");
        }
    }
}
