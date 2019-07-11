package com.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] passaros;
	private Texture fundo;
	private Texture canoBaixo;
	private Texture canoTopo;
	private Random numeroRandomico;
	private BitmapFont fonte;
	private Circle passarocirculo;
	private Rectangle retanguloCanoTopo;
	private Rectangle retanguloCanoBaixo;
	private ShapeRenderer shape;


	//Atributos de configuração
	private int laguraDispositivo;
	private int alturaDispositivo;
	private int estadoJogo=0;// 0-> jogo não iniciado 1-> jogo iniciado
    private int pontuacao=0;

	private float variacao=0;
	private float velocidadeQueda=0;
	private float posicaoInicialVertical;
	private float posicaoMovimentoCanoHorizontal;
	private float espacoEntreCanos;
	private float deltaTime;
    private float alturaEntreCanosRadomica;
    private boolean marcouPonto=false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		numeroRandomico = new Random();
		passarocirculo = new Circle();
		retanguloCanoTopo = new Rectangle();
		retanguloCanoBaixo = new Rectangle();
        shape = new ShapeRenderer();


		fonte = new BitmapFont();
		fonte.setColor(Color.WHITE);
		fonte.getData().setScale(6);

		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");
		canoBaixo = new Texture("cano_baixo.png");
		canoTopo = new Texture("cano_topo.png");

		laguraDispositivo= Gdx.graphics.getWidth();
		alturaDispositivo=Gdx.graphics.getHeight();
		posicaoInicialVertical=alturaDispositivo/2;
		posicaoMovimentoCanoHorizontal = laguraDispositivo-100;
		espacoEntreCanos = 200;
	}

	@Override
	public void render () {
        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 5;

        if (variacao > 2) {
            variacao = 0;
        }

	    if( estadoJogo == 0 ){ //Não iniciado
	        if( Gdx.input.justTouched() ){
	            estadoJogo = 1;
            }
        } else {

            Gdx.app.log("Variacao", "Variacao" + deltaTime);
            posicaoMovimentoCanoHorizontal -= deltaTime * 200;
            velocidadeQueda++;

            if (Gdx.input.justTouched()) {
                Gdx.app.log("Toque", "Toque na tela");

                //posicaoInicialVertical =posicaoInicialVertical - velocidadeQueda
                //5
                velocidadeQueda = -20;
            }

            if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
                posicaoInicialVertical -= velocidadeQueda;
            }

            //Verifica se o cano saiu da tela
            if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                posicaoMovimentoCanoHorizontal = laguraDispositivo;
                alturaEntreCanosRadomica = numeroRandomico.nextInt(400) - 200;
                marcouPonto = false;
            }

            //Verifica pontuacao
            if(posicaoMovimentoCanoHorizontal<120) {
                if(!marcouPonto){
                    pontuacao++;
                    marcouPonto = true;
                }
            }

        }
		batch.begin();

        drawBackground();
        drawPipes();
        drawBird();
        fonte.draw(batch, String.valueOf(pontuacao), laguraDispositivo/2, alturaDispositivo-50);

		batch.end();

		passarocirculo.set(120 + passaros[0].getWidth() / 2 , posicaoInicialVertical + passaros[0].getHeight() / 2, passaros[0].getWidth()/2);
        retanguloCanoBaixo = new Rectangle(
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + alturaEntreCanosRadomica,
                canoBaixo.getWidth(),
                canoBaixo.getHeight()
        );

        retanguloCanoTopo = new Rectangle(
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo/2 + espacoEntreCanos/2 + alturaEntreCanosRadomica,
                canoTopo.getWidth(),
                canoTopo.getHeight());

		//Desenhar formas
        shape.begin( ShapeRenderer.ShapeType.Filled );
        shape.circle(passarocirculo.x, passarocirculo.y, passarocirculo.radius);
        shape.rect(retanguloCanoBaixo.x, retanguloCanoBaixo.y, retanguloCanoBaixo.width, retanguloCanoBaixo.height);
        shape.rect(retanguloCanoTopo.x, retanguloCanoTopo.y, retanguloCanoTopo.width, retanguloCanoTopo.height);
        shape.setColor(Color.RED);
        shape.end();
	}

	public void drawBackground(){
        batch.draw(fundo, 0,0, laguraDispositivo, alturaDispositivo);
    }

    public void drawPipes() {
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo/2 + espacoEntreCanos/2 + alturaEntreCanosRadomica);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + alturaEntreCanosRadomica);
    }

    public void drawBird() {
        batch.draw(passaros[(int)variacao], 120, posicaoInicialVertical);
    }


	/*
	 * O método dispose () do ApplicationListener é o método
	 * do ciclo de vida e chamado quando o aplicativo é destruído.
	 * Quaisquer recursos descartáveis que você criou no método create ()
	 * devem ser destruídos nesse método.
	  * */
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
		batch.dispose();
		passaros[0].dispose();
        passaros[1].dispose();
        passaros[2].dispose();
		fundo.dispose();
	}
}