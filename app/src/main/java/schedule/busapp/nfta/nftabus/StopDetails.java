package schedule.busapp.nfta.nftabus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StopDetails extends AppCompatActivity {

    TextView stop_name;
    TextView stop_direction;
    TextView buses_at_this_stop;
    String _stop_name, _stop_direction, _buses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_details);

        stop_name =(TextView) findViewById(R.id.stop_name);
        stop_direction =(TextView) findViewById(R.id.bus_direction);
        buses_at_this_stop=(TextView) findViewById(R.id.buses);

        _stop_name = getIntent().getExtras().getString("stop_name");
        _buses = getIntent().getExtras().getString("buses");
        _stop_direction = getIntent().getExtras().getString("stop_direction");
        stop_name.setText(_stop_name);
        stop_direction.setText(_stop_direction);
        buses_at_this_stop.setText(_buses);
    }

}
