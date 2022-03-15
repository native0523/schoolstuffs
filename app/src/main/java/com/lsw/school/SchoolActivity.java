package com.lsw.school;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class SchoolActivity extends AppCompatActivity {

    //디비클래스와 디비헬퍼클래스
    SQLiteDatabase db;
    MySQLiteOpenHelper_School helper;//★클래스 + 변수

    //전환프레임 4개
    LinearLayout ll1, ll2, ll3, ll4;

    //입력텍스트 (관리번호, 기자재명, 단가, 개수, 관리자명)
    EditText etNum1, etNum2, etNum3, etNum4, etStuff1, etDate1, etPrice1, etPrice2, etMany1, etMany2, etMng1, etMng2;

    //출력텍스트
    TextView tvSen1, tvSen2, tvSen3, tvSen4, tvExpl;

    //홈버튼과 프레임버튼 이벤트
    Button selMenu, upMenu, delMenu, inMenu, btnSelect1, btnSelect3, btnSelectAll, btnUpdate, btnDelete, btnInsert;

    //insert 처리변수
    String num, name, date, price, many, person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        helper = new MySQLiteOpenHelper_School(
                SchoolActivity.this,
                "school.db",
                null,
                2);


        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);


        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        etNum3 = (EditText) findViewById(R.id.etNum3);
        etNum4 = (EditText) findViewById(R.id.etNum4);
        etStuff1 = (EditText) findViewById(R.id.etStuff1);
        etDate1 = (EditText) findViewById(R.id.etDate1);
        etPrice1 = (EditText) findViewById(R.id.etPrice1);
        etPrice2 = (EditText) findViewById(R.id.etPrice2);
        etMany1 = (EditText) findViewById(R.id.etMany1);
        etMany2 = (EditText) findViewById(R.id.etMany2);
        etMng1 = (EditText) findViewById(R.id.etMng1);
        etMng2 = (EditText) findViewById(R.id.etMng2);


        tvSen1 = (TextView) findViewById(R.id.tvSen1);
        tvSen2 = (TextView) findViewById(R.id.tvSen2);
        tvSen3 = (TextView) findViewById(R.id.tvSen3);
        tvSen4 = (TextView) findViewById(R.id.tvSen4);
        tvExpl = (TextView) findViewById(R.id.tvExpl);


        selMenu = (Button) findViewById(R.id.selMenu);
        upMenu = (Button) findViewById(R.id.upMenu);
        delMenu = (Button) findViewById(R.id.delMenu);
        inMenu = (Button) findViewById(R.id.inMenu);
        btnSelect1 = (Button) findViewById(R.id.btnSelect1);
        btnSelect3 = (Button) findViewById(R.id.btnSelect3);
        btnSelectAll = (Button) findViewById(R.id.btnSelectAll);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert = (Button) findViewById(R.id.btnInsert);


        selMenu.setOnClickListener(mClickListener);
        upMenu.setOnClickListener(mClickListener);
        delMenu.setOnClickListener(mClickListener);
        inMenu.setOnClickListener(mClickListener);
        btnSelect1.setOnClickListener(mClickListener);
        btnSelect3.setOnClickListener(mClickListener);
        btnSelectAll.setOnClickListener(mClickListener);
        btnUpdate.setOnClickListener(mClickListener);
        btnDelete.setOnClickListener(mClickListener);
        btnInsert.setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {

            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.INVISIBLE);
            ll3.setVisibility(View.INVISIBLE);
            ll4.setVisibility(View.INVISIBLE);

            switch (v.getId()) {
                case R.id.selMenu:
                    ll1.setVisibility(View.VISIBLE);
                    break;
                case R.id.upMenu:
                    ll2.setVisibility(View.VISIBLE);
                    break;
                case R.id.delMenu:
                    ll3.setVisibility(View.VISIBLE);
                    break;
                case R.id.inMenu:
                    ll4.setVisibility(View.VISIBLE);
                    break;

                case R.id.btnInsert:
                    ll4.setVisibility(View.VISIBLE);

                    String strNum4 = etNum4.getText().toString().trim();
                    String strStuff1 = etStuff1.getText().toString().trim();
                    String strDate1 = etDate1.getText().toString().trim();
                    String strPrice2 = etPrice2.getText().toString().trim();
                    String strMany2 = etMany2.getText().toString().trim();
                    String strMng2 = etMng2.getText().toString().trim();

                    if (strNum4.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } num = strNum4;
                    if (strStuff1.equals("")) {
                        Toast.makeText(SchoolActivity.this, "기자재명을 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } name = strStuff1;
                    if (strDate1.equals("")) {
                        Toast.makeText(SchoolActivity.this, "입고일을 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } date = strDate1;
                    if (strPrice2.equals("")) {
                        Toast.makeText(SchoolActivity.this, "단가를 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } price = strPrice2;

                    if (strMany2.equals("")) {
                        Toast.makeText(SchoolActivity.this, "수량을 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } many = strMany2;
                    if (strMng2.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리자명을 입력하세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } person = strMng2;

                    insert(num, name, date, price, many, person);
                    break;

                case R.id.btnSelect1:
                    ll1.setVisibility(View.VISIBLE);
                    num = etNum1.getText().toString();
                    if (num.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    select(num);
                    break;

                case R.id.btnSelectAll:
                    ll1.setVisibility(View.VISIBLE);
                    selectAll();
                    break;

                case R.id.btnSelect3:
                    ll3.setVisibility(View.VISIBLE);
                    tvExpl.setText("");
                    tvSen3.setText("");

                    num = etNum3.getText().toString();
                    if (num.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        tvExpl.setText("관리번호를 입력하세요.");
                        break;
                    }
                    searchDelNum(num);
                    break;

                case R.id.btnDelete:
                    ll3.setVisibility(View.VISIBLE);
                    num = etNum3.getText().toString();
                    if (num.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        tvExpl.setText("관리번호를 입력하세요.");
                        break;
                    }
                    deleteNum(num);
                    break;

                case R.id.btnUpdate:
                    ll2.setVisibility(View.VISIBLE);
                    num = etNum2.getText().toString();
                    price = etPrice1.getText().toString();
                    many = etMany1.getText().toString();
                    person = etMng1.getText().toString();

                    if (num.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    if (price.equals("")) {
                        Toast.makeText(SchoolActivity.this, "단가를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    if (many.equals("")) {
                        Toast.makeText(SchoolActivity.this, "수량을 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    if (person.equals("")) {
                        Toast.makeText(SchoolActivity.this, "관리자를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    update(num, price, many, person);
            }
        }
    };

    public void insert(String num, String name, String date, String price, String many, String person) {

        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // db.insert의 매개변수인 values가 ContentValues 변수이므로 그에 맞춤
        // 데이터의 삽입은 put을 이용한다.
        values.put("num", num);
        values.put("name", name);
        values.put("date", date);
        values.put("price", price);
        values.put("many", many);
        values.put("person", person);

        db.insert("school", null, values); // 테이블/널컬럼핵/데이터(널컬럼핵=디폴트). 널컬럼핵 null로 입력안하면 NULL이라고 써짐
        db.close();

        tvSen4.setText("관리번호 : " + num + ", 기자재명 : " + name + ", 등재연월 : " + date + ", 단가 : " + price + ", 수량 : " + many + ", 관리자명 : " + person + "\n");
        Toast.makeText(getApplicationContext(), num + " 의 정보가 입력되었습니다.", Toast.LENGTH_LONG).show();
        Log.d("정보입력", "관리번호 : " + num + "의 정보가 입력되었습니다.");

        etNum4.setText("");
        etStuff1.setText("");
        etDate1.setText("");
        etPrice2.setText("");
        etMany2.setText("");
        etMng2.setText("");
    }

    public void select(String searchNum) {

        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM school where num ='" + searchNum + "'", null);

        String result="";
        while (c.moveToNext()) {
            int idx = c.getInt(0);
            String num = c.getString(1);
            String name = c.getString(2);
            String date = c.getString(3);
            String price = c.getString(4);
            String many = c.getString(5);
            String person = c.getString(6);

            Log.d("특정조회", "번호 : " + idx + " / 관리번호 : " + num + " / 기자재명 : " + name
                    + " / 등재연월 : " + date + " / 단가 : " + price + " / 수량 : " + many + " / 관리자명 : " + person);
            Toast.makeText(getApplicationContext(),  "관리번호 : " + num + " 를 조회했습니다.", Toast.LENGTH_LONG).show();
            result += "번호 : " + idx + " / 관리번호 : " + num + " / 기자재명 : " + name + " / 등재연월 : " + date + " / 단가 : " + price + " / 수량 : " + many + " / 관리자명 : " + person + "\n";
        }

        tvSen1.setText(result);
        c.close();
        db.close();
    }

    public void selectAll() {

        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from school", null);


        String result = "";
        while (c.moveToNext()) {
            int idx = c.getInt(0);
            String num = c.getString(1);
            String name = c.getString(2);
            String date = c.getString(3);
            String price = c.getString(4);
            String many = c.getString(5);
            String person = c.getString(6);

            Log.d("전체조회", "번호 : " + idx + "관리번호 : " + num + " / 기자재명 : " + name
                    + " / 등재연월 : " + date + " / 단가 : " + price + " / 수량 : " + many + " / 관리자명 : " + person);
            Toast.makeText(getApplicationContext(),  "전체물품을 조회했습니다.", Toast.LENGTH_LONG).show();
            result += "번호 : " + idx + " / 관리번호 : " + num + " / 기자재명 : " + name + " / 등재연월 : " + date + " / 단가 : " + price + " / 수량 : " + many + " / 관리자명 : " + person + "\n\n";
        }

        tvSen1.setText(result);
        c.close();
        db.close();
    }

    public void searchDelNum(String searchNum) {

        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM school where num = '" + searchNum + "'", null);

        String result = "";
        while (c.moveToNext()) {
            String num = c.getString(1);
            String name = c.getString(2);
            String date = c.getString(3);
            String price = c.getString(4);
            String many = c.getString(5);
            String person = c.getString(6);

            Log.d("삭제할번호조회", "관리번호 : " + num + " / 기자재명 : " + name + " / 등재연월 : ");
            Toast.makeText(getApplicationContext(),  "관리번호 : " + num + " 이(가) 조회되었습니다.", Toast.LENGTH_LONG).show();
            result += "관리번호 : " + num + " / 기자재명 : " + name + " / 등재연월 : " + date + " / ...\n";
        }
        c.close();
        db.close();
        tvExpl.setText("관리번호가 조회되었습니다.");
        tvSen3.setText(result);
    }

    public void deleteNum(String searchNum) {

        db = helper.getWritableDatabase();
        db.delete("school", "num = '"+ searchNum + "'", null);

        Log.i("정보삭제", "관리번호 : " + searchNum + " 삭제");
        Toast.makeText(getApplicationContext(),  "관리번호 : " + searchNum + " 이(가) 삭제되었습니다.", Toast.LENGTH_LONG).show();

        tvExpl.setText(searchNum + "이(가) 삭제되었습니다.");
        tvSen3.setText("");
        db.close();
    }

    public void update(String num, String price, String many, String person) {

        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("num", num);
        values.put("price", price);
        values.put("many", many);
        values.put("person", person);
        db.update("school", values, "num='" + num + "'", null);
        db.close();

        Log.i("정보삭제", "관리번호 " + num + " 정보수정");
        Toast.makeText(getApplicationContext(), "관리번호 " + num + " 의 정보가 수정되었습니다.", Toast.LENGTH_LONG).show();

        //현재 정보를 보여주기
        tvSen2.setText("관리번호 : " + num + " / 수정된 단가 : " + price + " / 수정된 수량 : " + many + " / 수정된 관리자명 : " + person);

        etNum2.setText("");
        etPrice1.setText("");
        etMany1.setText("");
        etMng1.setText("");
    }












}