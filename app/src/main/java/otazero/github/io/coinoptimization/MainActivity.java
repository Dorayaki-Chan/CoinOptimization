package otazero.github.io.coinoptimization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.text.TextWatcher;
        import android.text.Editable;
        import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity implements TextWatcher{

    /* 保持値取得 */
    /*
    SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
    int yukichiS = data.getInt("Yukichi", 0);
    int ichiyoS = data.getInt("Ichiyo", 0);
    int shureiS = data.getInt("Shurei", 0);
    int hideyoS = data.getInt("Hideyo", 0);
    int gomarumaruS = data.getInt("Gomarumaru", 0);
    int hitomarumaruS = data.getInt("Hitomarumaru", 0);
    int gomaruS = data.getInt("Gomaru", 0);
    int hitomaruS = data.getInt("Hitomaru", 0);
    int goS = data.getInt("Go", 0);
    int hitoS = data.getInt("Hito", 0);
     */


    /* My create */
    private EditText[] editTextHenkan = new EditText[10];
    private EditText kaikei;

    private TextView[] textViewKaikei = new TextView[10];
    private TextView textViewTsuri;
    private TextView textViewShoji;
    private TextView textViewShiharai;

    //通貨
    private int[] tmpKahei = {10000, 5000, 2000, 1000, 500, 100, 50, 10, 5, 1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* example */
        Button button = findViewById(R.id.button);

        /* My create */
        editTextHenkan[0] = findViewById(R.id.editTextNumber_Yukichi);
        editTextHenkan[1] = findViewById(R.id.editTextNumber_Ichiyo);
        editTextHenkan[2] = findViewById(R.id.editTextNumber_Shurei);
        editTextHenkan[3] = findViewById(R.id.editTextNumber_Hideyo);
        editTextHenkan[4] = findViewById(R.id.editTextNumber_Gomarumaru);
        editTextHenkan[5] = findViewById(R.id.editTextNumber_Hitomarumaru);
        editTextHenkan[6] = findViewById(R.id.editTextNumber_Gomaru);
        editTextHenkan[7] = findViewById(R.id.editTextNumber_Hitomaru);
        editTextHenkan[8] = findViewById(R.id.editTextNumber_Go);
        editTextHenkan[9] = findViewById(R.id.editTextNumber_Hito);
        kaikei = findViewById(R.id.editTextNumber_Kaikei);

        textViewKaikei[0] = findViewById(R.id.text_view_sheet_Yukichi);
        textViewKaikei[1] = findViewById(R.id.text_view_sheet_Ichiyo);
        textViewKaikei[2] = findViewById(R.id.text_view_sheet_Shurei);
        textViewKaikei[3] = findViewById(R.id.text_view_sheet_Hideyo);
        textViewKaikei[4] = findViewById(R.id.text_view_sheet_Gomarumaru);
        textViewKaikei[5] = findViewById(R.id.text_view_sheet_Hitomarumaru);
        textViewKaikei[6] = findViewById(R.id.text_view_sheet_Gomaru);
        textViewKaikei[7] = findViewById(R.id.text_view_sheet_Hitomaru);
        textViewKaikei[8] = findViewById(R.id.text_view_sheet_Go);
        textViewKaikei[9] = findViewById(R.id.text_view_sheet_Hito);

        textViewTsuri = findViewById(R.id.textView_tsuri);
        textViewShoji = findViewById(R.id.text_view_shoji);

        textViewShiharai = findViewById(R.id.textView_shiharai);

        //随時更新
        for (int i = 0; i < tmpKahei.length; i++){
            editTextHenkan[i].addTextChangedListener(this);
        }


        /* ボタンが押されたら */
        button.setOnClickListener( v-> {

            /* example */

            /* My create */
            String strkaikei = kaikei.getText().toString();
            int numkaikei = 0;
            if (strkaikei.length() > 0) {
                numkaikei = Integer.parseInt(strkaikei);
            }
            System.out.println(numkaikei);


            //集合Aとその合計
            int sumA = 0;
            String[] strHenkan = new String[10];
            int shugoA[] = new int[10];
            for (int i = 0; i < editTextHenkan.length; i++){
                strHenkan[i] = editTextHenkan[i].getText().toString();
                if (strHenkan[i].length() > 0) {
                    shugoA[i] = Integer.parseInt(strHenkan[i]);
                }
                else{
                    shugoA[i] = 0;
                }
                sumA += shugoA[i] * tmpKahei[i];
            }


            //集合B
            int sumB = sumA - numkaikei;
            int shugoB[] = new int[10];
            int shugoC[] = new int[10];
            int shugoA01[] = new int[10];
            int shugoB01[] = new int[10];
            int otsuri = 0;
            int shiharai = 0;
            for (int i = 0; i < tmpKahei.length; i++){
                int count = 0;
                for (int j = tmpKahei[i]; sumB >= j; sumB -= j){
                    count++;
                }
                shugoB[i] = count;

                //集合C
                shugoC[i] =shugoA[i] > shugoB[i] ? shugoB[i]:shugoA[i];

                //集合A'と集合B'
                shugoA01[i] = shugoA[i] - shugoC[i];
                shugoB01[i] = shugoB[i] - shugoC[i];

                //おつり計算
                otsuri += shugoB01[i] * tmpKahei[i];

                //支払額計算
                shiharai += shugoA01[i] * tmpKahei[i];

                //支払い枚数出力
                textViewKaikei[i].setText(String.valueOf(shugoA01[i]));
            }
            textViewTsuri.setText(String.valueOf(otsuri));
            textViewShiharai.setText(String.valueOf(shiharai));
        });
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {



        // テキスト変更後に変更されたテキストを取り出す
        int sumA = 0;
        String[] strHenkan = new String[10];
        int shugoA[] = new int[10];
        for (int i = 0; i < editTextHenkan.length; i++){
            strHenkan[i] = editTextHenkan[i].getText().toString();
            if (strHenkan[i].length() > 0) {
                shugoA[i] = Integer.parseInt(strHenkan[i]);
            }
            else{
                shugoA[i] = 0;
            }
            sumA += shugoA[i] * tmpKahei[i];
        }
        textViewShoji.setText(String.valueOf(sumA));


        /* 値保持 */
        /*
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("Yukichi", shugoA[0]);
        editor.putInt("Ichiyo", shugoA[1]);
        editor.putInt("Shurei", shugoA[2]);
        editor.putInt("Hideyo", shugoA[3]);
        editor.putInt("Gomarumaru", shugoA[4]);
        editor.putInt("Hitomarumaru", shugoA[5]);
        editor.putInt("Gomaru", shugoA[6]);
        editor.putInt("Hitomaru", shugoA[7]);
        editor.putInt("Go", shugoA[8]);
        editor.putInt("Hito", shugoA[9]);
        editor.apply();
        */
    }
}