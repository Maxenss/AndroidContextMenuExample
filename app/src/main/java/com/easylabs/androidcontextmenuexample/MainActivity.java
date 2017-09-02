package com.easylabs.androidcontextmenuexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends

        AppCompatActivity {
    Button bt1;
    Button bt2;
    Button bt3;
    Button btAllButtonVisible;
    Button btTemp;

    EditText et1;
    EditText et2;
    EditText etTemp;

    // id для элементов контекстного меню
    final int MENU_INVISIBLE = 1;
    final int MENU_GONE = 2;

    final int MENU_CLEAR_TEXT = 3;
    final int MENU_SHOW_TEXT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем кнопки
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        btAllButtonVisible =
                (Button) findViewById(R.id.btAllButtonVisible);
        btAllButtonVisible.setOnClickListener(btAllButtonVisibleClick);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        // Привязывем кнопки к контекстному меню
        registerForContextMenu(bt1);
        registerForContextMenu(bt2);
        registerForContextMenu(bt3);
        registerForContextMenu(et1);
        registerForContextMenu(et2);
    }

    // Метод вызываемый при вызове контекстного меню
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v instanceof Button) {
            // TODO Auto-generated method stub
            menu.add(0, MENU_INVISIBLE, 0, "Invisible");
            menu.add(0, MENU_GONE, 0, "Gone");
            // Записываем ссылку, для какой кнопки вызвано контекстное меню
            btTemp = (Button) v;
        }
        else if (v instanceof EditText){
            menu.add(0, MENU_CLEAR_TEXT, 0, "Clear Text");
            menu.add(0, MENU_SHOW_TEXT, 0, "Show Text");
            etTemp = (EditText) v;
        }

    }

    // Метод вызываемый при выборе контекстного меню
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_INVISIBLE:
                btTemp.setVisibility(View.INVISIBLE);
                break;
            case MENU_GONE:
                btTemp.setVisibility(View.GONE);
                break;
            case MENU_CLEAR_TEXT :
                etTemp.setText("");
                break;
            case MENU_SHOW_TEXT:
                Toast.makeText(this,
                        etTemp.getText().toString(),
                        Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    // Обработчик на нажатие кнопки btAllButtonVisible
    View.OnClickListener btAllButtonVisibleClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Делаем все кнопки видимыми
            bt1.setVisibility(View.VISIBLE);
            bt2.setVisibility(View.VISIBLE);
            bt3.setVisibility(View.VISIBLE);
        }
    };
}
