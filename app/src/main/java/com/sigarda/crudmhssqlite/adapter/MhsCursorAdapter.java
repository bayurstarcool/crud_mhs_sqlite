package com.sigarda.crudmhssqlite.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigarda.crudmhssqlite.EditMahasiswa;
import com.sigarda.crudmhssqlite.ListMahasiswa;
import com.sigarda.crudmhssqlite.R;

public class MhsCursorAdapter extends CursorAdapter
{

    /**
     * Konstruktor
     * @param context konteks activity
     * @param cursor cursor dari query
     */
    public MhsCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor,0);
    }

    /**
     * Membuat view baru
     * @param context konteks activity
     * @param cursor cursor query
     * @param viewGroup viewGroup
     * @return View
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_mahasiswa,viewGroup,false);
    }

    /**
     * Bind view dengan data. Dijalankan dengan iterasi
     *

     */
    private AlertDialog AskOption(Context context,String NIM)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete "+NIM+" ?")
                .setIcon(R.drawable.ic_delete)

                .setPositiveButton("Delete", (dialog, whichButton) -> {
                    //your deleting code
                    ((ListMahasiswa)context).deleteMhs(NIM);
                    dialog.dismiss();
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView nameV = view.findViewById(R.id.mhs_name);
        TextView nimV = view.findViewById(R.id.mhs_nim);
        ImageView edit = view.findViewById(R.id.edit);
        ImageView delete = view.findViewById(R.id.delete);
        String NIM = cursor.getString(cursor.getColumnIndexOrThrow("NIM"));
        String NAME = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        delete.setOnClickListener(del->{
            AlertDialog diaBox = AskOption(context,NIM);
            diaBox.show();
        });
        edit.setOnClickListener(click->{
            Intent i = new Intent(context, EditMahasiswa.class);
            i.putExtra("name",NAME);
            i.putExtra("nim",NIM);
            context.startActivity(i);
        });
        nameV.setText(NAME);
        nimV.setText(NIM);
    }
}