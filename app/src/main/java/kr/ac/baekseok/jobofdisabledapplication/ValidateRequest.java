package kr.ac.baekseok.jobofdisabledapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {
    final static private String idurl = "http://ftpdot.dothome.co.kr/checkID.php";
    private Map<String, String>parameters;

    public ValidateRequest(String uid, Response.Listener<String>listener){
        super(Method.POST,idurl,listener,null);
        parameters=new HashMap<>();
        parameters.put("uid",uid);
    }

    protected Map<String, String> getParameters() throws AuthFailureError{
        return parameters;
    }
}
