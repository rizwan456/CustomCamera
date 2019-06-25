package com.rizz.customcamera;


import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rizz.customcamera.databinding.FragmentBsdfilterBinding;
import com.rizz.customcamera.model.Filter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BSDFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BSDFilterFragment extends BottomSheetDialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentBsdfilterBinding bsdfilterBinding;
    IFilterClicked iFilterClicked;


    @SuppressLint("ValidFragment")
    public BSDFilterFragment(IFilterClicked iFilterClicked) {
        this.iFilterClicked = iFilterClicked;
    }

    public BSDFilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BSDFilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BSDFilterFragment newInstance(String param1, String param2) {
        BSDFilterFragment fragment = new BSDFilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bsdfilterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bsdfilter, container, false);
        setUp();
        return bsdfilterBinding.getRoot();
    }

    private void setUp() {
        bsdfilterBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        bsdfilterBinding.recyclerView.setAdapter(new FilterAdapter(generateFilter(), getActivity(),iFilterClicked));
        bsdfilterBinding.cancelBtn.setOnClickListener(v -> {
            this.dismiss();
        });
        bsdfilterBinding.doneBtn.setOnClickListener(v -> {
            this.dismiss();
        });
    }

    public List<Filter> generateFilter() {
        List<Filter> list = new ArrayList<>();
        list.add(new Filter(1, "Normal"));
        list.add(new Filter(2, "H1"));
        list.add(new Filter(3, "O2"));
        list.add(new Filter(4, "B3"));
        list.add(new Filter(5, "H2"));
        list.add(new Filter(6, "J6"));
        list.add(new Filter(7, "R1"));
        list.add(new Filter(8, "j9"));
        list.add(new Filter(9, "D1"));
        list.add(new Filter(10, "L1"));
        list.add(new Filter(11, "R5"));
        list.add(new Filter(12, "H11"));
        return list;
    }

}
