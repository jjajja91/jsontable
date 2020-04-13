package org.comstudy21.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Dao {
	private String pathname = "src/org/comstudy21/resource/student.json";
	private FileReader reader;
	private FileWriter writer;

	private static Dao instance;

	public static Dao getInstance() {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}

	private JSONObject getJsonObject() {
		File dataFile = new File(pathname);
		try {
			reader = new FileReader(dataFile);
			JSONTokener token = new JSONTokener(reader);
			return new JSONObject(token);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<Vector> selectAll() {
		JSONObject jsonObject = getJsonObject();
		if (jsonObject == null) {
			System.out.println("데이터가 없습니다!");
			return null;
		}

		JSONArray jsonArray = jsonObject.getJSONArray("studentArr");

		Vector<Vector> vector = new Vector<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			Vector v = new Vector<>();
			v.add(obj.get("no"));
			v.add(obj.get("name"));
			v.add(obj.get("email"));
			v.add(obj.get("phone"));
			vector.add(v);
		}
		return vector;
	}

	public void delete(Vector<Integer> idxVector) {

		JSONObject jsonObject = getJsonObject();
		if (jsonObject == null) {
			System.out.println("처리 할 데이터가 없습니다!");
			return;
		}
		boolean flag = true;
		JSONArray jsonArray = jsonObject.getJSONArray("studentArr");
		JSONArray newArray = new JSONArray();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject tmp = jsonArray.getJSONObject(i);
			for (int j = 0; j < idxVector.size(); j++) {
				if (idxVector.get(j) == tmp.getInt("no")) {
					flag = false;
					break;
				}
			}
			if (flag) {
				newArray.put(tmp);
			}
		}

		JSONObject object = new JSONObject();
		object.put("studentArr", newArray);
		object.put("seq", jsonObject.get("seq"));

		try {
			writer = new FileWriter(pathname);
		} catch (IOException e) {
			System.out.println("저장할 파일이 없습니다");
		}
		try {
			writer.write(object.toString(2));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Vector<Vector> selectSome(Member dto) {
		JSONObject jsonObject = getJsonObject();
		if (jsonObject == null) {
			System.out.println("데이터가 없습니다!");
			return null;
		}

		JSONArray jsonArray = jsonObject.getJSONArray("studentArr");

		Vector<Vector> vector = new Vector<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			Vector v = new Vector<>();
			String name = "" + obj.get("name");
			String email = "" + obj.get("email");
			String phone = "" + obj.get("phone");
			if (name.indexOf(dto.getName()) != -1) {
				if (email.indexOf(dto.getEmail()) != -1) {
					if (phone.indexOf(dto.getPhone()) != -1) {
						v.add(obj.get("no"));
						v.add(obj.get("name"));
						v.add(obj.get("email"));
						v.add(obj.get("phone"));
						vector.add(v);
					}
				}
			}
		}
		return vector;
	}

	public void insert(Member dto) {
		// 리스트를 가져옴 -> 리스트에 dto 값을 추가 -> 다시 저장

		File file = new File(pathname);
		if (!file.exists()) {
			JSONObject newObj = new JSONObject();
			newObj.put("no", 1);
			newObj.put("name", dto.getName());
			newObj.put("email", dto.getEmail());
			newObj.put("phone", dto.getPhone());
			JSONArray newArray = new JSONArray();
			newArray.put(newObj);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("seq", 2);
			jsonObject.put("studentArr", newArray);
			try {
				writer.write(jsonObject.toString(2));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
					if (writer != null) {
						writer.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		JSONObject jsonObject = getJsonObject();
		if (jsonObject == null) {
			System.out.println("처리 할 데이터가 없습니다!");
			return;
		}
		JSONArray jsonArray = jsonObject.getJSONArray("studentArr");
		JSONObject dtoObject = new JSONObject();
		int seq = jsonObject.getInt("seq");
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			System.out.println("저장할 파일이 없습니다");
		}
		dtoObject.put("no", seq++);
		dtoObject.put("name", dto.getName());
		dtoObject.put("email", dto.getEmail());
		dtoObject.put("phone", dto.getPhone());
		jsonArray.put(dtoObject);

		JSONObject object = new JSONObject();
		object.put("studentArr", jsonArray);
		object.put("seq", seq);

		try {
			writer.write(object.toString(2));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}