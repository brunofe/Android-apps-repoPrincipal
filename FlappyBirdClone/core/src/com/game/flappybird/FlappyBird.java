package com.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] passaros;
	private Texture fundo;

	//Atributos de configuração
	private int laguraDispositivo;
	private int alturaDispositivo;

	private float variacao=0;
	private float velocidadeQueda=0;
	private float posicaoInicialVertical;
	@Override
	public void create () {
		batch = new SpriteBatch();
		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");

		laguraDispositivo= Gdx.graphics.getWidth();
		alturaDispositivo=Gdx.graphics.getHeight();
		posicaoInicialVertical=alturaDispositivo/2;
	}

	@Override
	public void render () {

	    variacao += Gdx.graphics.getDeltaTime()*5;
	    Gdx.app.log("Variacao","Variacao"+Gdx.graphics.getDeltaTime());
		velocidadeQueda++;

	    if(variacao > 2){
	        variacao = 0;
        }

	    if(posicaoInicialVertical>0){
			posicaoInicialVertical -= velocidadeQueda;
		}

		batch.begin();

		batch.draw(fundo, 0,0, laguraDispositivo, alturaDispositivo);
		batch.draw(passaros[(int)variacao], 30, posicaoInicialVertical);

		batch.end();
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
