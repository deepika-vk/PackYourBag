package com.example.packyourbag.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {
    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION="LAST_VERSION";
    public static final int NEW_VERSION=1;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }
    public List<Items> getBasicData() {
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<>();
        basicItem.clear();
        basicItem.add(new Items("Visa", category, false));
        basicItem.add(new Items("Passport", category, false));
        basicItem.add(new Items("Tickets", category, false));
        basicItem.add(new Items("Wallet", category, false));
        basicItem.add(new Items("Driving License", category, false));
        basicItem.add(new Items("Currency", category, false));
        basicItem.add(new Items("House Key", category, false));
        basicItem.add(new Items("Book", category, false));
        basicItem.add(new Items("Travel Pillow", category, false));
        basicItem.add(new Items("Eye Patch", category, false));
        basicItem.add(new Items("Note Book", category, false));
        return basicItem;

    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash", "Shaving Cream", "Rajor blade", "Soap", "Fiber", "Shampoo", "Hair Conditioner", "Brush"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);


    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Snapsuit", "Outfit", "Jumpsuit", "Baby socks", "Baby hat", "Baby Pyjamas", "Baby bath towel", "Muslin", "Blanket", "Dribble bids", "Baby Bottles"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE, data);
    }
    public List<Items> getTechnologyData() {
        String[] data = {"Mobile Phone","Phone cover","Camera","Camera charger","IPad","Headphone","Battery","Power Bank","Flash-Light","SD card"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }
    public List<Items> getFoodData() {
        String[] data = {"Snacks","Sandwich","Juice","Tea Bags","Coffee","Water","Thermos","Chips","baby Food"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"Stockings", "Underwear", "Pajamas", "T-shirts", "Casual Dress", "Evening Dress", "shirt", "Cardigan", "Vest", "jacket", "Skirt"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);

    }
    public List<Items> getBeachSuppliesData() {
        String[] data = {"sea Glasses","beach umbrella","beach slippers","sun glasses","waterproof glasses"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }
    public List<Items> getCarSuppliesData() {
        String[] data = {"pump","CarJack","Space Car Key","Accident Record Set","auto refridgerator","car cover","Window sun shades","Car charger"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE, data);
    }
    public List<Items> getNeedsData() {
        String[] data = {"Backpack","daily bags","Laundry bag","Saving kit","Travel Lock","Magazine","Sports Equipment","Important Number"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE, data);
    }
    public List<Items> getHealthData() {
        String[] data = {"First Aid Kit","Pain Reliever","fever reduction","Pain Relieve Spray","Aspirin","Hot Water bag"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE, data);
    }
    public List<Items>prepareItemsList(String category, String[] data){
        List<String> list= Arrays.asList(data);
        List<Items>dataList=new ArrayList<>();
        dataList.clear();

        for (int i=0;i<list.size();i++){
            dataList.add(new Items(list.get(i),category,false ));
        }
        return dataList;
    }
    public List<List<Items>> getAllData() {
        List<List<Items>>ListOfAllItems= new ArrayList<>();
        ListOfAllItems.clear();
        ListOfAllItems.add(getBasicData());
        ListOfAllItems.add(getClothingData());
        ListOfAllItems.add(getBabyNeedsData());
        ListOfAllItems.add(getPersonalCareData());
        ListOfAllItems.add(getTechnologyData());
        ListOfAllItems.add(getHealthData());
        ListOfAllItems.add(getFoodData());
        ListOfAllItems.add(getNeedsData());
        ListOfAllItems.add(getCarSuppliesData());
        ListOfAllItems.add(getBeachSuppliesData());
        return ListOfAllItems;
    }
    public void persistAllData(){
        List<List<Items>> listofallItems=getAllData();
        for(List<Items>list:listofallItems){
            for(Items items:list) {
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data added.");
    }

    public void persistDataByCategory(String category,Boolean onlyDelete){
        try{
          List<Items> list=deleteAndGetListByCategory(category,onlyDelete);
        if(!onlyDelete){
           for(Items item:list) {
             database.mainDao().saveItem(item);
           }
            Toast.makeText(context, category+"Reset Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, category+"Reset Successfully", Toast.LENGTH_SHORT).show();
        }
        }catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(context, "Something Went wrong", Toast.LENGTH_SHORT).show();
        }
    }
   private List<Items> deleteAndGetListByCategory(String category, Boolean onlyDelete){
        if(onlyDelete){
            database.mainDao().deleteAllCategoryAndAddedBy(category,MyConstants.SYSTEM_SMALL);
        }else{
            database.mainDao().deleteAllByCategory(category);
        }
        switch (category){
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();
            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();
            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();
            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();
            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();
            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();
            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();
            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();
            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSuppliesData();
            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();
            default:
                return new ArrayList<>();

        }
   }

}
