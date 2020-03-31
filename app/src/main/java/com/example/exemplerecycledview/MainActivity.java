package com.example.exemplerecycledview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exemplerecycledview.Entities.Adherent;
import com.example.exemplerecycledview.Entities.Adherents;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //On recupere notre Widget RecyclerView
        RecyclerView rcvAdherents = findViewById(R.id.rcvAdherents);
        //On creer notre Liste
        Adherents adherents = getAdherents();
        //On Instancie l'Adapter
        AdherentAdapter adherentAdapter = new AdherentAdapter(adherents);

        //Maniere dont s'affiche les adherents : Linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        //Maniere dont s'affiche les adherents : GridView
        RecyclerView.LayoutManager layoutManagerEnGridView = new GridLayoutManager(context,3);

        //Creer une animation

        rcvAdherents.setItemAnimator(new DefaultItemAnimator());

        //On passe notre adapter a notre recyclerView
        rcvAdherents.setLayoutManager(layoutManager);
        rcvAdherents.setAdapter(adherentAdapter);

    }

    private Adherents getAdherents(){
        Adherents adherents = new Adherents();
        Adherent adherent = null;

        for (int i = 0; i < 10 ; i++){
            adherent = new Adherent("tata"+i,"toto"+i,20+i);
            adherents.add(adherent);
        }

        return adherents;
    }

    public class AdherentHolder extends RecyclerView.ViewHolder{
        //On declare les widgets
        public final TextView txtNom;
        public final TextView txtPrenom;
        public final TextView txtAge;

        //Constructeur du AdherentHolder
        public AdherentHolder(@NonNull View itemView) {
            super(itemView);

            //On instencie les widgets qui se trouve dans la View "ItemView"
            txtNom = itemView.findViewById(R.id.txtNom);
            txtPrenom = itemView.findViewById(R.id.txtPrenom);
            txtAge = itemView.findViewById(R.id.txtAge);
        }

        //On crée un class qui permet de charger notre Adherent dans chaque Item
        public void setAdherent (Adherent adherent) {
            txtNom.setText(adherent.getNom());
            txtPrenom.setText(adherent.getPrenom());
            txtAge.setText(""+adherent.getAge());
        }
    }

    public class AdherentAdapter extends RecyclerView.Adapter<AdherentHolder>{
        //On declare notre liste en globale
        Adherents adherents;

        //On instancie dans la liste via un constructeur
        public AdherentAdapter(Adherents adherents) {
            this.adherents = adherents;
        }

        @NonNull
        @Override
        public AdherentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //On convertit une layoutItem en View pour la passer dans un holder
            View view = LayoutInflater.from(context).inflate(R.layout.item_adherent,parent,false);
            //On retourne un nouveau Holder avec notre View view creer a partir d'un layout Item
            return new AdherentHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdherentHolder holder, int position) {
            //Rechercher un item à la position "position"
            Adherent adherent = this.adherents.get(position);
            //On passe l'objet à notre holder
            holder.setAdherent(adherent);
        }

        @Override
        public int getItemCount() {
            return adherents.size();
        }
    }
}
