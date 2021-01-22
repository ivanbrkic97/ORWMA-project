package dev.brkic.anniething.fragments;

public class MatchesFragment  extends Fragment {
    private TextView mMessageTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessageTextView = view.findViewById(R.id.tvMessage);
    }
    public void displayMessage(String message) {
        mMessageTextView.setText(!message.trim().isEmpty() ? message : "...");
    }
}