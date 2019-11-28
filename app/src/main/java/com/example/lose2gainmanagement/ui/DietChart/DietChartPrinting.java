package com.example.lose2gainmanagement.ui.DietChart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.fonts.Font;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.StringFormatter;
import com.example.lose2gainmanagement.ui.foods.FoodItems;
import com.example.lose2gainmanagement.ui.foods.FoodViewModel;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.io.font.FontCacheKey;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.Background;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.styledxmlparser.jsoup.nodes.Element;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.kernel.pdf.PdfName.BackgroundColor;
import static com.itextpdf.kernel.pdf.PdfName.BaseFont;
import static com.itextpdf.kernel.pdf.PdfName.Color;
import static com.itextpdf.kernel.pdf.PdfName.Encode;
import static com.itextpdf.kernel.pdf.PdfName.Encoding;
import static com.itextpdf.kernel.pdf.PdfName.Image;

public class DietChartPrinting extends AppCompatActivity {

    private File pdfFile;
    private PdfDocument pdfDocument;
    private Document document;
    private PdfWriter pdfWriter;
    private FoodViewModel foodViewModel;
    private List<FoodItems> foodList;
    private Paragraph paragraph;
    final int STORAGE_CODE = 1;
    private PDFView pdfView;
    FloatingActionButton floatingActionButton;

    List<DietChartBela> dcbelaList = new ArrayList<>();
    List<DietChartFood> dcfoodList = new ArrayList<>();
    DietChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_chart_printing);

        pdfView = findViewById(R.id.pdf_viewer);
        floatingActionButton = findViewById(R.id.DCShare);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        foodViewModel = new ViewModelProvider( this).get(FoodViewModel.class);

        foodViewModel.getAllWords().observe( this, new Observer<List<FoodItems>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable final List<FoodItems> items) {
                // Update the cached copy of the words in the adapter.
                foodList = items;
                updateChart();
            }
        });


        chart = (DietChart) getIntent().getExtras().getSerializable("chart");



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");
                File pdf = new File(folder,"test.pdf");
                Uri uri = Uri.fromFile(pdf);
                sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
                sendIntent.setType("application/pdf");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void updateChart(){




        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,STORAGE_CODE);
            }else{
                creatDietChart();
            }
        }else{
            creatDietChart();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    creatDietChart();
                }
            }

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creatDietChart(){
        openDocument();
        /*addMetaData("Lose to gain","Diet Chart","Sajedur Rahman");
        addTitle("Lose To Gain","DietChart Report");
        addSection("Client Information");
        try {
            ClientInformation();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        addSection("Diet Chart");*/
        addTitle(chart.getChartName(),chart.getChartDate());
        addSection("Client Information");
        try {
            ClientInformation();
            addSection("Diet chart:");
            dietChart();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        closeDocument();



        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");

        File pdf = new File(folder,"test.pdf");
        pdfView.fromFile(pdf)
                .password(null)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAnnotationRendering(true)
                .load();

    }

    private void ClientInformation() throws UnsupportedEncodingException {
        paragraph = new Paragraph();
        paragraph.setHorizontalAlignment(HorizontalAlignment.LEFT);
        FontProgram fontProgram = null;
        try {
            fontProgram = FontProgramFactory.createFont("assets/nirmala.ttf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Table table = new Table(UnitValue.createPercentArray(2));
        table.setBorder(Border.NO_BORDER);
        com.itextpdf.layout.element.Image image = textToImage("নামঃ ", android.graphics.Color.BLACK);
        table.addCell(image);
        image = textToImage(chart.getcName(), android.graphics.Color.CYAN);
        table.addCell(image);
        image = textToImage("উচ্চতাঃ ", android.graphics.Color.BLACK);
        table.addCell(image);
        image = textToImage(chart.getcHeight(), android.graphics.Color.CYAN);
        table.addCell(image);
        image =textToImage( "ওজনঃ ", android.graphics.Color.BLACK);
        table.addCell(image);
        image =textToImage( chart.getcWeight(), android.graphics.Color.CYAN) ;
        table.addCell(image);
        image =textToImage( "বয়সঃ ", android.graphics.Color.BLACK) ;
        table.addCell(image);
        image =textToImage( chart.getcAge(), android.graphics.Color.CYAN) ;
        table.addCell(image);
        image =textToImage( "সেক্সঃ ", android.graphics.Color.BLACK);
        table.addCell(image);
        image =textToImage( chart.getcSex(), android.graphics.Color.CYAN);
        table.addCell(image);
        image =textToImage( "মেডিক্যাল প্রবলেমঃ ", android.graphics.Color.BLACK);
        table.addCell(image);
        image =textToImage( chart.getcMedicalProblem(), android.graphics.Color.CYAN);
        table.addCell(image);

        document.add(table);

    }

    private void dietChart() throws UnsupportedEncodingException {

        double totalCal =0.0;
        double totalPro = 0.0;
        double totalFat = 0.0;
        double totalCarb = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");

        paragraph = new Paragraph();
        paragraph.setHorizontalAlignment(HorizontalAlignment.LEFT);
        FontProgram fontProgram = null;
        try {
            fontProgram = FontProgramFactory.createFont("assets/nirmala.ttf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Table table = new Table(UnitValue.createPercentArray(7));
        com.itextpdf.layout.element.Image image;

        String[] titles ={"বেলা","খাবার","পরিমাণ","ক্যালরি","প্রোটিন","ফ্যাট","কার্ব"};

        for(String title: titles){
            image = textToImage(title, android.graphics.Color.BLACK);
            Cell cell = new Cell(1,1);
            cell.add(image);
            cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(cell);
        }
        for(DietChartBela bela:chart.getBelas()){
            image = textToImage(bela.getBela(), android.graphics.Color.BLACK);
            Cell cell = new Cell(bela.getFoodItems().size(),1);
            cell.add(image);
            table.addCell(cell);
            for(DietChartFood food: bela.getFoodItems()){
                image = textToImage(food.getFood().getfName(), android.graphics.Color.BLACK);
                table.addCell(image);
                image = textToImage(englishToBangla(food.getAmount()) + " "+ food.getFood().getfQuantity(), android.graphics.Color.BLACK);
                table.addCell(image);

                int amount = Integer.parseInt(banglaToEnglish(food.getAmount()));
                int preAmount = Integer.parseInt(banglaToEnglish(food.getFood().getfAmount()));
                double cal = Double.parseDouble(banglaToEnglish(food.getFood().getfCallories()));
                double pro = Double.parseDouble(banglaToEnglish(food.getFood().getfProten()));
                double fat = Double.parseDouble(banglaToEnglish(food.getFood().getfFat()));
                double carb = Double.parseDouble(banglaToEnglish(food.getFood().getfCarb()));

                cal = (cal/preAmount) * amount;
                pro = (pro/preAmount) * amount;
                fat = (fat/preAmount) * amount;
                carb = (carb/preAmount) * amount;

                totalCal += cal;
                totalPro += pro;
                totalFat += fat;
                totalCarb += carb;

                image = textToImage(englishToBangla(df.format(cal)), android.graphics.Color.BLACK);
                table.addCell(image);
                image = textToImage(englishToBangla(df.format(pro)), android.graphics.Color.BLACK);
                table.addCell(image);
                image = textToImage(englishToBangla(df.format(fat)), android.graphics.Color.BLACK);
                table.addCell(image);
                image = textToImage(englishToBangla(df.format(carb)), android.graphics.Color.BLACK);
                table.addCell(image);
            }

            cell = new Cell(1,7);
            table.addCell(cell);
        }

        Cell cell = new Cell(3,1);
        table.addCell(cell);

        cell = new Cell(1,2);
        image = textToImage("টোটালঃ", android.graphics.Color.BLACK);
        cell.add(image);
        table.addCell(cell);

        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(cell);

        image = textToImage(englishToBangla(df.format(totalPro)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);

        image = textToImage(englishToBangla(df.format(totalFat)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);

        image =textToImage(englishToBangla(df.format(totalCarb)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);

        cell = new Cell(1,2);
        image = textToImage("ক্যালরিঃ", android.graphics.Color.BLACK);
        cell.add(image);
        table.addCell(cell);

        totalPro = Math.round(totalPro * 4);
        totalFat = Math.round(totalFat * 9);
        totalCarb = Math.round(totalCarb * 4);
        totalCal = totalPro + totalFat + totalCarb;

        image = textToImage(englishToBangla(String.valueOf(totalCal)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalPro)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalFat)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalCarb)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);

        cell = new Cell(1,2);
        image = textToImage("ম্যাক্রো %", android.graphics.Color.BLACK);
        cell.add(image);
        table.addCell(cell);

        totalPro = Math.round((totalPro / totalCal)*100);
        totalFat = Math.round((totalFat / totalCal)*100);
        totalCarb = Math.round((totalCarb / totalCal)*100);

        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalPro)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalFat)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);
        image = textToImage(englishToBangla(String.valueOf(totalCarb)), android.graphics.Color.BLACK);
        cell = new Cell(1,1);
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell.add(image);
        table.addCell(cell);

        document.add(table);

        paragraph = new Paragraph();
        paragraph.setHorizontalAlignment(HorizontalAlignment.LEFT);
        image =textToImage("নির্দেশনাবলী", android.graphics.Color.RED);
        paragraph.add(image);
        paragraph.add("\n");
        image =textToImage(chart.getInstruction(), android.graphics.Color.BLACK);
        paragraph.add(image);
        paragraph.setBackgroundColor(ColorConstants.YELLOW);
        paragraph.setPadding(20);

        document.add(paragraph);

    }

    public void openDocument(){
        createFile();
        try {
            pdfWriter = new PdfWriter(pdfFile);
            pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            document = new Document(pdfDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void closeDocument(){
        document.close();
    }

    public void addTitle(String title,String date){
        paragraph = new Paragraph();
        paragraph.setTextAlignment(TextAlignment.CENTER);
        Image image = textToImage(title, android.graphics.Color.BLACK);
        image.setHeight(30);
        paragraph.add(image);
        paragraph.add("\n");
        image = textToImage(date, android.graphics.Color.BLACK);
        image.setHeight(20);
        paragraph.add(image);
        paragraph.add(title).setFontSize(22);
        document.add(paragraph);
    }

    public void addSection(String SectionName){
        paragraph = new Paragraph();
        paragraph.setHorizontalAlignment(HorizontalAlignment.LEFT);
        paragraph.add(SectionName);
        document.add(paragraph);
    }

    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");

        if(!folder.exists()){
            System.out.println("hoise");
            folder.mkdir();
        }

        pdfFile = new File(folder,"test.pdf");
    }



    public Image textToImage(String text,int color){

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(50);
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent();
        int witdh = (int) (paint.measureText(text) + 0.5f);
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap bitmap = Bitmap.createBitmap(witdh,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text,0,baseline,paint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        return new Image(ImageDataFactory.create(byteArray)).setHeight(15);
    }


    public String englishToBangla(String value){
        Character bangla_number[]={'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
        Character eng_number[]={'0','1','2','3','4','5','6','7','8','9'};
        String values = "";
        char[] character = value.toCharArray();
        for (int i=0; i<character.length ; i++) {
            Character c = ' ';
            for (int j = 0; j < eng_number.length; j++) {
                if(character[i]==eng_number[j])
                {
                    c=bangla_number[j];
                    break;
                }else {
                    c=character[i];
                }
            }
            values=values+c;
        }
        return values;
    }

    public String banglaToEnglish(String value){
        Character bangla_number[]={'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
        Character eng_number[]={'0','1','2','3','4','5','6','7','8','9'};
        String values = "";
        char[] character = value.toCharArray();
        for (int i=0; i<character.length ; i++) {
            Character c = ' ';
            for (int j = 0; j < bangla_number.length; j++) {
                if(character[i]==bangla_number[j])
                {
                    c=eng_number[j];
                    break;
                }else {
                    c=character[i];
                }
            }
            values=values+c;
        }
        return values;
    }


}
