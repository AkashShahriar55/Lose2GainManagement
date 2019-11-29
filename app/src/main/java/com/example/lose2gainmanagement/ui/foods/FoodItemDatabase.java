package com.example.lose2gainmanagement.ui.foods;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FoodItems.class}, version = 1)
public abstract class FoodItemDatabase extends RoomDatabase {
    public abstract AccessFood accessFood();

    private static volatile FoodItemDatabase INSTANCE;

    static FoodItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodItemDatabase.class, "l2g_database").addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            new PopulateDbAsync(INSTANCE).execute();
                        }
                    })
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AccessFood mDao;

        PopulateDbAsync(FoodItemDatabase db) {
            mDao = db.accessFood();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            FoodItems item = new FoodItems("পনির","১০০","গ্রাম","৩০০","২০","২৩.৩","৩.৩");
            mDao.insertFood(item);
            item = new FoodItems("ডিম","১","পিস","৭২","৬","৫","০");
            mDao.insertFood(item);
            item = new FoodItems("হোয়ে প্রোটিন","৩০","গ্রাম","১২০","২৪","১","৩");
            mDao.insertFood(item);
            item = new FoodItems("মিক্সড ভেজিটেবল","১","কাপ","৪৬","৩","২","৪");
            mDao.insertFood(item);
            item = new FoodItems("চিজ স্লাইস","১","পিস","৬২","৪","৫","০.৩");
            mDao.insertFood(item);
            item = new FoodItems("চিনি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ঘি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("বাটার","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("নারিকেল তেল","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("টক দই","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("কাঠ বাদাম","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ডিমের সাদা","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("চাল","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("আলু","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("সয়া নাগেট","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("বাটার","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("চিকেন ব্রেস্ট","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("চিকেন/মাছ চামড়া ছাড়া","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("গরুর মাংস","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("পেঁয়াজ","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("টমেটো","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("দুধ ফুল ফ্যাট","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("পালং শাক","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("বাঁধাকপি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ইসপগুল ভুষি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("মাল্টিভিটামিন","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("সিভিট","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("পানি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ভাত","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("আপেল","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("কমলা","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("কলা","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("পপকর্ন","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("রুটি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("মসূর ডাল","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ওটস","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("মুগ ডাল","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ছোলা","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ডাবের পানি","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("কিডনি বিনস/ রাজমা","১০০","গ্রাম","৩৮৭","০","০","১০০");
            mDao.insertFood(item);
            item = new FoodItems("ঢাকাই পনির","১০০","গ্রাম","৪০০","২০","৩২","৪");
            mDao.insertFood(item);
            item = new FoodItems("ওলিভ অয়েল","১০","গ্রাম","৮৬","০","১০","০");
            mDao.insertFood(item);
            item = new FoodItems("যব","১০০","গ্রাম","৩৫৪","১২.৫","২.৩","৫৬");
            mDao.insertFood(item);
            item = new FoodItems("গুঁড়া দুধ","১০০","গ্রাম","৫১০","২৪","২৬","৩৭");
            mDao.insertFood(item);
            item = new FoodItems("তিসি","১০০","গ্রাম","৫৩৪","১৮.৩","৪২","১.৬");
            mDao.insertFood(item);
            item = new FoodItems("দুধ চা স্টেভিয়া দিয়ে","১","কাপ","৪০","২.১","২.১","৩.১");
            mDao.insertFood(item);
            item = new FoodItems("দুধ কফি স্টেভিয়া দিয়ে","১","কাপ","৪৫","২.১","৩","৪");
            mDao.insertFood(item);


            return null;
        }
    }
}
