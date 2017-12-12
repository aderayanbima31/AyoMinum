package com.example.iihsa.airminum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.iihsa.airminum.constants.Constants;
import com.example.iihsa.airminum.controller.AppController;
import com.google.firebase.iid.FirebaseInstanceId;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class KategoriActivity extends AppCompatActivity {
    @Bind(R.id.berat)
    EditText berat;
    String token;
    String id_user ="";
    @Bind(R.id.circleImageView3)
    CircleImageView circleImageView;
    String nama, username, email, password;
    int success;
    String TAG_SUCCESS = "success";
    String TAG_MESSAGE="message";
    String tag_json_obj = "json_obj_req";
    Bitmap bitmap;
    @Bind(R.id.nama)TextView textView;
    @Bind(R.id.coodinatorLayout)
    CoordinatorLayout coordinatorLayout;
    private static final String TAG = KategoriActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        nama = extras.getString("nama");
        username = extras.getString("username");
        email = extras.getString("email");
        password = extras.getString("password");

        textView.setText(""+nama+""+username+""+email+""+password);
    }
    @OnClick(R.id.floatingActionButton7)
    void ambilfoto(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    @OnClick(R.id.slsai)
    void selesai(){
//        token = FirebaseInstanceId.getInstance().getToken();
        uploadData();
    }
    private void uploadData() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Upload Data");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);
                            if (success != 1) {
                                Log.d("v Add", jObj.toString());
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Registrasi Berhasil", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else {
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Registrasi Gagal", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            loading.dismiss();
                             Toast.makeText(getApplicationContext(),"Error Respone",Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Error Respone",Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<String, String>();
                //menambah parameter yang di kirim ke web servis
//                params.put("token", token);
                params.put("username", username);
                params.put("nama", nama);
                params.put("foto", getStringImage(bitmap));
                params.put("beratbadan", berat.getText().toString());
                params.put("password", password);
                //kembali ke parameters
                Log.d(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        try {
            bitmap = (Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Belum Mengambil Foto", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
