package com.fdmgroup.typingspeedtester.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class InputWordList {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="inputwordlist_gen")
	@SequenceGenerator(name="inputwordlist_gen", sequenceName ="INPUTWORDLIST_SEQ", allocationSize=1)
	private long inputWordListId;
	
	@Column
	private ArrayList<String> inputWordListWords;
	
	public InputWordList() {
	}

	public InputWordList(ArrayList<String> inputWordList) {
		super();
		this.inputWordListWords = inputWordList;
	}

	public long getInputWordListId() {
		return inputWordListId;
	}

	public void setInputWordListId(long inputWordListId) {
		this.inputWordListId = inputWordListId;
	}

	public ArrayList<String> getInputWordListValues() {
		return inputWordListWords;
	}

	public void setInputWordListValues(ArrayList<String> inputWordList) {
		this.inputWordListWords = inputWordList;
	}

	@Override
	public String toString() {
		return "InputWordList [inputWordListId=" + inputWordListId + ", inputWordList=" + inputWordListWords + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputWordListWords == null) ? 0 : inputWordListWords.hashCode());
		result = prime * result + (int) (inputWordListId ^ (inputWordListId >>> 32));
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
		InputWordList other = (InputWordList) obj;
		if (inputWordListWords == null) {
			if (other.inputWordListWords != null)
				return false;
		} else if (!inputWordListWords.equals(other.inputWordListWords))
			return false;
		if (inputWordListId != other.inputWordListId)
			return false;
		return true;
	}
	
}
