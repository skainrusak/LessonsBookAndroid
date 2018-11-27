package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container , false);

        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycle_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
class CrimeHolder extends RecyclerView.ViewHolder{
    public TextView mTitleTextView;

    public CrimeHolder (View itemView){
        super(itemView);
        mTitleTextView = (TextView)itemView;
    }

}
class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
    private List<Crime>mCrimes;

    public CrimeAdapter(List<Crime>crimes){
        mCrimes = crimes;
    }
    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent , int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1 , parent , false);
        return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(CrimeHolder holder , int position){
        Crime crime = mCrimes.get(position);
        holder.mTitleTextView.setText(crime.getTitle());
    }
    public int getItemCount(){
        return mCrimes.size();
    }



}
