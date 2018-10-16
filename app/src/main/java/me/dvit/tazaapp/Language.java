package me.dvit.tazaapp;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.view.View;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
public class Language {

    SharedPreferences.Editor LanguageEditor;
    public static final String Language_PREFS = "languagePrefs";

    public  void setLanguage(View view , String language){
        LanguageEditor = view.getContext().getSharedPreferences(Language_PREFS, MODE_PRIVATE).edit();
        LanguageEditor.putString("language",language);
        LanguageEditor.commit();
    }


    public String getLanguage(Context context){
        SharedPreferences  prefs = context.getSharedPreferences(Language_PREFS, MODE_PRIVATE);
        String language = prefs.getString("language","en");//"No name defined" is the default value.
        setLocale(context ,language);

        return language;
    }
    public void setLocale(Context context ,String lang) {
        String languageToLoad  = lang; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
        } else {
            config.locale = locale;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            config.setLayoutDirection(locale);
        }
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());
    }
}
