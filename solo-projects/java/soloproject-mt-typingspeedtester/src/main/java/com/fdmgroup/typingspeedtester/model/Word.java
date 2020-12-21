package com.fdmgroup.typingspeedtester.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="words")
public class Word {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="word_gen")
	@SequenceGenerator(name="word_gen", sequenceName ="WORD_SEQ", allocationSize=1)
	private long wordId;

	@Column(nullable=false, length=100, unique=true)
	private String word;
	
	public Word() {
	}

	public Word(String word, ArrayList<String> builtListOfRandomWords) {
		super();
		this.word = word;
	}

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + (int) (wordId ^ (wordId >>> 32));
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
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (wordId != other.wordId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Word [wordId=" + wordId + ", word=" + word + "]";
	}
	
}
