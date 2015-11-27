package com.alertview;

import com.bigkoo.alertviewdemo.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


/**
 * ����iOSAlertViewController�ؼ�Demo
 */
public class MainActivity extends Activity implements OnItemClickListener, OnDismissListener {

    private AlertView mAlertView;//���ⴴ���ظ�View���ȴ���View��Ȼ����Ҫ��ʱ��show�������Ƽ��������
    private AlertView mAlertViewExt;//������չ����
    private EditText etName;//��չView����
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mAlertView = new AlertView("����", "����", "ȡ��", new String[]{"ȷ��"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        //��չ����
        mAlertViewExt = new AlertView("��ʾ", "��������ĸ������ϣ�", "ȡ��", null, new String[]{"���"}, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //���������������ƶ�
                boolean isOpen=imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen&&focus ? 120 :0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    public void alertShow1(View view) {
        mAlertView.show();
    }

    public void alertShow2(View view) {
        new AlertView("����", "����", null, new String[]{"ȷ��"}, null, this, AlertView.Style.Alert, this).show();
    }

    public void alertShow3(View view) {
        new AlertView(null, null, null, new String[]{"������ť1", "������ť2", "������ť3"},
                new String[]{"������ť1", "������ť2", "������ť3", "������ť4", "������ť5", "������ť6",
                        "������ť7", "������ť8", "������ť9", "������ť10", "������ť11", "������ť12"},
                this, AlertView.Style.Alert, this).show();
    }

    public void alertShow4(View view) {
        new AlertView("����", null, "ȡ��", new String[]{"������ť1"}, new String[]{"������ť1", "������ť2", "������ť3"}, this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShow5(View view) {
        new AlertView("����", "����", "ȡ��", null, null, this, AlertView.Style.ActionSheet, this).setCancelable(true).show();
    }

    public void alertShow6(View view) {
        new AlertView("�ϴ�ͷ��", null, "ȡ��", null,
                new String[]{"����", "�������ѡ��"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }
    private void closeKeyboard() {
        //�ر������
        imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
        //�ָ�λ��
        mAlertViewExt.setMarginBottom(0);
    }
    @Override
    public void onItemClick(Object o,int position) {
        closeKeyboard();
        //�ж��Ƿ�����չ����View�����ҵ�����Ƿ�ȡ����ť
        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
            String name = etName.getText().toString();
            if(name.isEmpty()){
                Toast.makeText(this, "ɶ��û����", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
            }

            return;
        }
        Toast.makeText(this, "����˵�" + position + "��", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "��ʧ��", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(mAlertView!=null && mAlertView.isShowing()){
                mAlertView.dismiss();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}
