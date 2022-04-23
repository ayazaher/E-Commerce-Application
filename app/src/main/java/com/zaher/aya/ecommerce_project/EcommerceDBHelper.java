package com.zaher.aya.ecommerce_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EcommerceDBHelper extends SQLiteOpenHelper {
    private static String databasename="EcommerceDatabase";
    SQLiteDatabase EcommerceDB;

    public EcommerceDBHelper(@Nullable Context context) {
        super(context, databasename, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Categories(CatID integer primary key autoincrement,catname text)");
        db.execSQL("create table Customer (CustID integer primary key autoincrement,custname text not null,email text not null,password text not null, gender text,birthofdate text,job text )");

        db.execSQL("create table Orders (OrdID integer primary key autoincrement,orddate text,Address text,custid integer,FOREIGN KEY(custid)REFERENCES Customer(CustID))");

        db.execSQL("create table Product(ProdID integer primary key autoincrement,proname text,price integer,quantity integer,catid integer,FOREIGN KEY(catid)REFERENCES Categories(CatID))");
        db.execSQL("create table OrderDetails (OrdID integer,ProdID integer ,quantity text,FOREIGN KEY(OrdID)REFERENCES Orders(OrdID),FOREIGN KEY(ProdID)REFERENCES Product(ProdID),primary key(OrdID,ProdID))");


        db.execSQL("insert into Categories ('catname') values('Laptop')");
        db.execSQL("insert into Categories ('catname') values('Clothes')");
        db.execSQL("insert into Categories ('catname') values('Shoes')");

        db.execSQL("insert into Product('proname','price','quantity','catid') values('Samsung','25000','5','1')");
        db.execSQL("insert into Product('proname','price','quantity','catid') values(Product_Details,'30000','2','1')");

        db.execSQL("insert into Product('proname','price','quantity','catid') values('jacket','700','4','2')");
        db.execSQL("insert into Product('proname','price','quantity','catid') values('short','400','2','2')");

        db.execSQL("insert into Product('proname','price','quantity','catid') values('black shoes','500','3','3')");
        db.execSQL("insert into Product('proname','price','quantity','catid') values('white shoes','450','4','3')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Categories");
        db.execSQL("drop table if exists Customer");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists Product");
        db.execSQL("drop table if exists OrderDetails");

        onCreate(db);
    }
    public boolean insert(String custname, String email, String password, String gender,String birthofdate,  String job) {
        EcommerceDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("custname", custname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        contentValues.put("birthofdate", birthofdate);
        contentValues.put("job", job);
        long ins = EcommerceDB.insert("Customer", null, contentValues);
        if (ins == -1) {
            EcommerceDB.close();
            return false;
        }
        else {
            EcommerceDB.close();
            return true;
        }
    }

    public boolean checkemai(String email) {
        EcommerceDB = getReadableDatabase();
        Cursor cursor = EcommerceDB.rawQuery("select * from Customer where email like ?", new String[]{"%"+email+"%"});
        if (cursor.getCount() > 0)
        {
            EcommerceDB.close();
            return false;
        }

        else {
            EcommerceDB.close();
            return true;
        }
    }

    public boolean email_password(String email, String password) {
        EcommerceDB = getReadableDatabase();
        Cursor cursor = EcommerceDB.rawQuery("select * from Customer where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            EcommerceDB.close();
            return true;
        }
        else
        {
            EcommerceDB.close();
            return false;
        }

    }

    public int quantity(String proname){
        EcommerceDB=getReadableDatabase();

        Cursor cursor=EcommerceDB.rawQuery("select quantity from Product where proname= "+proname,null);
        cursor.moveToFirst();
        EcommerceDB.close();
        return cursor.getInt(0);

    }

    public String forgotpass(String email){
        EcommerceDB=getReadableDatabase();
        Cursor cursor = EcommerceDB.rawQuery("select password  from Customer where email=? ", new String[]{email});
        cursor.moveToFirst();
        EcommerceDB.close();
        return cursor.getString(0);
    }
    public Cursor getallcateg(){
        EcommerceDB=getReadableDatabase();
        String[] rowdetails={"CatID","catname"};
        Cursor cursor=EcommerceDB.query("Categories",rowdetails,null,null,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        EcommerceDB.close();
        return cursor;

    }
    public  int get_cat_id(String cat_name){
        EcommerceDB=getReadableDatabase();
        String[]arr={cat_name};
        Cursor cursor=EcommerceDB.rawQuery("select * from Categories where catname like ?",arr);
        if (cursor!=null)
            cursor.moveToFirst();
        EcommerceDB.close();
        return cursor.getInt(0);
    }
    public Cursor getproducts(int catid){
        EcommerceDB=getReadableDatabase();

        Cursor cursor=EcommerceDB.rawQuery("select * from Product where catid= "+catid,null);
        if (cursor!=null)
            cursor.moveToFirst();
        EcommerceDB.close();
        return cursor;
    }
    public Cursor getpro(String name,int catid){
        EcommerceDB=getReadableDatabase();
        Cursor cursor=EcommerceDB.rawQuery("Select * from Product where proname like'%" + name +"%' and catid like '%" + catid +"%' ",null);
        if (cursor.getCount()!=0)
            cursor.moveToFirst();
        EcommerceDB.close();
        return cursor;
    }
    public String getdcatName(int catid)
    {
        EcommerceDB =getReadableDatabase();
        Cursor cursor2= EcommerceDB.rawQuery("select catname from Categories where CatID = " + catid,null);
        cursor2.moveToFirst();
        EcommerceDB.close();
        return cursor2.getString(0);
    }

    public String getproname(int proid){
        EcommerceDB=getReadableDatabase();

        Cursor cursor=EcommerceDB.rawQuery("select proname from Product where ProdID= "+proid,null);
        cursor.moveToFirst();
        EcommerceDB.close();
        return cursor.getString(0);

    }



    public  int getprodid(String proname){
        EcommerceDB=getReadableDatabase();
        String[]arr={proname};
        Cursor cursor=EcommerceDB.rawQuery("select * from Product where proname like ?",arr);
        if (cursor!=null)
            cursor.moveToFirst();
        EcommerceDB.close();
        return cursor.getInt(0);
    }




    public Cursor getproductdata(int prodid) {
        EcommerceDB = getReadableDatabase();
        Cursor Product_Details = EcommerceDB.rawQuery("SELECT * FROM Product WHERE ProdID=" + prodid, null);
        if (Product_Details != null) {
            Product_Details.moveToFirst();
        }
        EcommerceDB.close();
        return Product_Details;

    }


    public Cursor fetchallOrders(int cust_id)
    {
        EcommerceDB = getReadableDatabase();
        Cursor cursor = EcommerceDB.rawQuery("SELECT * FROM Orders where custid="+cust_id, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        EcommerceDB.close();
        return cursor;
    }

    public void addOrders(String orderDate, Integer CustID, String Address) {
        ContentValues row = new ContentValues();
        row.put("orddate", orderDate );
        row.put("Address", Address);
        row.put("custid", CustID);
        EcommerceDB = getWritableDatabase();
        EcommerceDB.insert("Orders", null, row);
        EcommerceDB.close();

    }
    public int getLastOrdID()
    {
        EcommerceDB = getReadableDatabase();
        Cursor cursor = EcommerceDB.rawQuery("SELECT MAX(OrdID) FROM Orders",null);
        int MaxOrderID = 0;
        if(cursor!=null)
        {
            cursor.moveToFirst();
            MaxOrderID = cursor.getInt(0);
        }
        EcommerceDB.close();
        return MaxOrderID;

    }


    public  void OrderDetails( Integer ProID, Integer OrdID, Integer Qty)
    {
        ContentValues row = new ContentValues();
        row.put("OrdID",OrdID);
        row.put("ProdID",ProID);
        row.put("quantity",Qty);
        EcommerceDB = getWritableDatabase();
        EcommerceDB.insert("OrderDetails",null,row);
        EcommerceDB.close();


    }

    public int getCustID(String email, String Password)
    {
        EcommerceDB = getReadableDatabase();
        int CustomerID = 0;
        Cursor cursor = EcommerceDB.rawQuery("SELECT CustID FROM Customer WHERE email Like '"+email+"' AND password LIKE '"+Password+"' ;",null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            CustomerID = cursor.getInt(0);
        }
        return CustomerID;
    }
    public int quant(int pro_id)
    {
        EcommerceDB=getReadableDatabase();

        Cursor cursor=EcommerceDB.rawQuery("select quantity from Product where ProdID= "+pro_id,null);
        cursor.moveToFirst();
        EcommerceDB.close();
        int newq=cursor.getInt(0);
        return newq;

    }
    public void subtractQuantity(int Product_ID, int Quantity)
    {
        EcommerceDB=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("quantity",Quantity);
        EcommerceDB.update("Product",cv,"ProdID="+Product_ID,null);
        EcommerceDB.close();


    }

}
