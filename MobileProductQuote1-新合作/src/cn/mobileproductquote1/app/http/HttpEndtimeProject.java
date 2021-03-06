package cn.mobileproductquote1.app.http;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import cn.mobileproductquote1.app.data.Project;
import cn.mobileproductquote1.app.util.HttpConstants;

/**
 * 获得截止的项目
 * 
 * @author Administrator
 * 
 */
public class HttpEndtimeProject extends BaseAsynHttpClient {

	private int status = HttpConstants.FAIL;
	private ArrayList<Project> list = new ArrayList<Project>();

	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		try {
			JSONObject jo = new JSONObject(content);
			status = jo.getInt("status");
			if (status == HttpConstants.SUCCESS) {
				String projectInfo = jo.getString("projectInfo");
				list = Project.getArray(projectInfo);
				if(list.size()<=0){
					setEmpty(true);
				}
			}else if(status==HttpConstants.EMPTY){
				setEmpty(true);
			}else{
				setFail(true);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Project> getList() {
		return list;
	}

	public void setList(ArrayList<Project> list) {
		this.list = list;
	}

}
