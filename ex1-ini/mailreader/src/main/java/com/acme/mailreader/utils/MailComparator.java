package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {
	
	public static final int PLUS_PETIT = 1;
	public static final int EGAUX = 0;
	public static final int PLUS_GRAND = -1;
	
	public int compare(Mail mail1, Mail mail2) {
		if (unDesMailNul(mail1, mail2)) {
			return EGAUX;
		}
		if (pasMemeImportance(mail1, mail2)) {
			return ordreParImportance(mail1, mail2);
		}
		if (pasMemeStatut(mail1, mail2)) {
			return ordreParStatut(mail1, mail2);
		}
		if (pasMemeSujet(mail1, mail2)) {
			return ordreParSujet(mail1, mail2);
		}
		return dateEgale(mail1, mail2);
	}
	
	private int ordreParStatut(Mail mail1, Mail mail2) {
		int comp = mail1.getStatut().ordinal()
				- mail2.getStatut().ordinal();
		return comp > 0 ? PLUS_PETIT  : PLUS_GRAND;
	}

	private int ordreParSujet(Mail mail1, Mail mail2) {
		return mail2.getSujet().compareTo(mail1.getSujet());
	}

	private boolean unDesMailNul(Mail mail1, Mail mail2) {
		return mail1 == null || mail2 == null;
	}

	private int dateEgale(Mail mail1, Mail mail2) {
		return mail2.getDate().compareTo(mail1.getDate());
	}

	private boolean pasMemeSujet(Mail mail1, Mail mail2) {
		return mail1.getSujet() != mail2.getSujet();
	}

	private boolean pasMemeStatut(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}

	private boolean pasMemeImportance(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}

	private int ordreParImportance(Mail mail1, Mail mail2) {
		if (mail1.isImportant() && !mail2.isImportant()) {
			return PLUS_GRAND;
		} else {
			return PLUS_PETIT;
		}
	}

}
