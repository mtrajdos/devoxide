package com.fdmgroup.typingspeedtester.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="randomwords")
public class RandomWordList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="randomwordlist_gen")
	@SequenceGenerator(name="randomwordlist_gen_gen", sequenceName ="RANDOMWORDLIST_SEQ", allocationSize=1)
	private long randomWordListId;
	
	private ArrayList<String> randomWordListWords;

	public RandomWordList() {
	}

	public RandomWordList(ArrayList<String> randomWordListWords) {
		super();
		this.randomWordListWords = randomWordListWords;
	}

	public long getRandomWordListId() {
		return randomWordListId;
	}

	public void setRandomWordListId(long randomWordListId) {
		this.randomWordListId = randomWordListId;
	}

	public ArrayList<String> getRandomWordListWords() {
		return randomWordListWords;
	}

	public void setRandomWordListWords(ArrayList<String> randomWordListWords) {
		this.randomWordListWords = randomWordListWords;
	}

	@Override
	public String toString() {
		return "RandomWordList [randomWordListId=" + randomWordListId + ", randomWordListWords=" + randomWordListWords
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (randomWordListId ^ (randomWordListId >>> 32));
		result = prime * result + ((randomWordListWords == null) ? 0 : randomWordListWords.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RandomWordList other = (RandomWordList) obj;
		if (randomWordListId != other.randomWordListId)
			return false;
		if (randomWordListWords == null) {
			if (other.randomWordListWords != null)
				return false;
		} else if (!randomWordListWords.equals(other.randomWordListWords))
			return false;
		return true;
	}
	
}
