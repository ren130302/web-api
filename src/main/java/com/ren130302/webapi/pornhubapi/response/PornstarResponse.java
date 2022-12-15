package com.ren130302.webapi.pornhubapi.response;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"categorie"
})
@Generated("jsonschema2pojo")
@Data
public class PornstarResponse {
	public String pornstar_name;
}
