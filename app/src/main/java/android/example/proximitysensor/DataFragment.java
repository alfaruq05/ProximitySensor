package android.example.proximitysensor;

import android.database.Cursor;
import android.example.proximitysensor.DatabaseHelpers.ProximityDatabaseHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textView1;
    private ProximityDatabaseHelper proximityDatabaseHelper;


    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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
        proximityDatabaseHelper = new ProximityDatabaseHelper(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView1 = view.findViewById(R.id.proximityOutputId);
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor =  proximityDatabaseHelper.retrieveData();
        StringBuilder stringBuffer = new StringBuilder();
        while (cursor.moveToNext()){
            stringBuffer.append("Id: ").append(cursor.getString(0)).append("\n");
            stringBuffer.append("Time: ").append(cursor.getString(1)).append("\n");
            stringBuffer.append("Value: ").append(cursor.getString(2)).append(" cm\n\n");
        }

        textView1.setText(stringBuffer.toString());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_data, container, false);
    }
}