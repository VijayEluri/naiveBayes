package br.usp.each.naiveBayes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.usp.each.naiveBayes.classes.BayesianClassification;
import br.usp.each.naiveBayes.classes.BayesianVocabulary;
import br.usp.each.naiveBayes.interfaces.Vocabulary;

public class BayesianClassificationDeveria {

	private static final String classificacao = "Classificação";

	@Test(expected = IllegalArgumentException.class)
	public void manterUmaClasseDeIdentificacaoNaoNula() {
		new BayesianClassification(null, null);
	}

	@Test
	public void manterUmVocabularioNaoNulo() {
		BayesianClassification bayesianClass = new BayesianClassification(classificacao, null);
		Vocabulary valorEncontrado = bayesianClass.getVocabulary();
		assertThat(valorEncontrado, CoreMatchers.notNullValue());
	}

	@Test
	public void calcularAProbabilidadeConhecidaDeUmaListaDePalavrasConhecidaPertencerAClasse() {
		Vocabulary x = new BayesianVocabulary();
		BayesianClassification bayesianClass = new BayesianClassification(classificacao, x );

		List<String> termList = Arrays.asList("roberto");
		bayesianClass.getVocabulary().add("julio");
		bayesianClass.getVocabulary().add("roberto");
		double probalidadeEsperada = 0.5;

		calculoEsperadoDeProbabilidadeDadoUmaClasseBayesianaEUmaListaDeTermos(
				termList, bayesianClass, probalidadeEsperada);
	}

	@Test
	public void calcularAProbabilidadeEsperadaDeUmaPalavraQuandoElaNaoPertenceAoVocabulario() {
		BayesianClassification bayesianClass = new BayesianClassification(classificacao, null);

		List<String> termList = Arrays.asList("roberto");
		bayesianClass.getVocabulary().add("julio");
		double probalidadeEsperada = 0.25;

		calculoEsperadoDeProbabilidadeDadoUmaClasseBayesianaEUmaListaDeTermos(
				termList, bayesianClass, probalidadeEsperada);
	}

	@Test
	public void calcularAProbabilidadeEsperadaDeUmaListaVazia() {
		BayesianClassification bayesianClass = new BayesianClassification(classificacao, null);
		List<String> termList = new ArrayList<String>();
		calculoEsperadoDeProbabilidadeDadoUmaClasseBayesianaEUmaListaDeTermos(
				termList, bayesianClass, 0);
	}

	@Test
	public void guardarTodasAsSuasClassificacoesEmMaiusculo() {
		BayesianClassification bayesianClass = new BayesianClassification("hoMERo", null);
		assertEquals("HOMERO", bayesianClass.getName());
	}

	private void calculoEsperadoDeProbabilidadeDadoUmaClasseBayesianaEUmaListaDeTermos(
			List<String> termList, BayesianClassification bayesianClass,
			double probalidadeEsperada) {
		double probabilidadeEncontrada = bayesianClass
				.getProbality(termList);
		assertEquals(probalidadeEsperada, probabilidadeEncontrada, 0.000001);
	}
}
