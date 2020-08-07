package br.com.selecao.indra.api.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Util {

	public static String passwordEncoder(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder.encode(password);
	}

	public static List<ObjectNode> formataJson(List<Tuple> results) {

		List<ObjectNode> json = new ArrayList<ObjectNode>();

		ObjectMapper mapper = new ObjectMapper();

		for (Tuple t : results) {
			List<TupleElement<?>> cols = t.getElements();

			ObjectNode one = mapper.createObjectNode();

			for (TupleElement<?> col : cols) {
				one.put(col.getAlias(), t.get(col.getAlias()).toString());
			}

			json.add(one);
		}

		return json;
	}
}
