package com.example.imagemrandomica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView img1, img2, img3;
    private Button btnVerificar, btnVerEstatistica, btnLimparEstatistica;
    private Random rnd;
    private TextView txtResultado;
    private EditText txtEstatistica;

    private int qtdeJogadaEfetuada = 0;
    private int qtdeJogadaGanha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Método para inicializar os objetos;
        InicializarObjetos();
    }

    private void InicializarObjetos() {
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtResultado.setText("");
        txtEstatistica = (EditText) findViewById(R.id.txtEstatistica);
        txtEstatistica.setText("");
        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Método para sortear e setar as imagens;
                SortearImagem();
            }
        });
        btnVerEstatistica = (Button) findViewById(R.id.btnVerEstatistica);
        btnVerEstatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Método para mostrar os dados da estatistica;
                MostrarEstatistica();
            }
        });
        btnLimparEstatistica = (Button) findViewById(R.id.btnLimparEstatistica);
        btnLimparEstatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Método para limpar os dados da estatistica;
                LimparEstatistica();
            }
        });
        MostrarBotoesEstatistica(false);
    }

    private void MostrarEstatistica() {
        txtEstatistica.setVisibility(View.VISIBLE);
    }

    private void MontarDadosDaEstatistica() {
        txtEstatistica.setText("");
        double media = qtdeJogadaGanha != 0 ? (double)(qtdeJogadaEfetuada / qtdeJogadaGanha) : 0.0f;
        String texto = String.format("Estatística \n\n  Qtde de jogadas: %s\n  Jogadas Ganhas: %s\n  Média: %.1f",
                qtdeJogadaEfetuada, qtdeJogadaGanha, media);
        txtEstatistica.setText(texto);
    }

    private void LimparEstatistica() {
        qtdeJogadaEfetuada = 0;
        qtdeJogadaGanha = 0;
        txtEstatistica.setText("");
        txtEstatistica.setVisibility(View.INVISIBLE);
        MostrarBotoesEstatistica(false);
    }

    private void SortearImagem() {
        Integer i1 = NumeroSorteado();
        SetarImagem(img1, i1);

        Integer i2 = NumeroSorteado();
        SetarImagem(img2, i2);

        Integer i3 = NumeroSorteado();
        SetarImagem(img3, i3);

        String resultado = ((i1==i2 && i2==i3) ? "GANHOU !!!" : "");
        Boolean ganhou = ((i1==i2 && i2==i3) ? true : false);
        AtualizarDadosDaEstatistica(ganhou);
        txtResultado.setText(resultado);
    }

    private void AtualizarDadosDaEstatistica(Boolean ganhou) {
        int ganho = ganhou == true ? 1: 0;
        qtdeJogadaGanha += ganho;
        qtdeJogadaEfetuada++;
        MostrarBotoesEstatistica(true);
        MontarDadosDaEstatistica();
    }

    private void MostrarBotoesEstatistica(Boolean estado) {
        int viewState = estado == true ? View.VISIBLE : View.INVISIBLE;
        btnVerEstatistica.setVisibility(viewState);
        btnLimparEstatistica.setVisibility(viewState);
    }

    private int NumeroSorteado() {
        rnd = new Random();
        Integer res = rnd.nextInt(3)+1;
        return res;
    }

    protected void SetarImagem(ImageView img, int imagem) {
        int id = getResources().getIdentifier(
                "domino"+String.valueOf(imagem),
                "drawable", getPackageName()
        );
        img.setImageResource(id);
    }
}
