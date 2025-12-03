package com.java4.sd20302.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoDeleteResponse {
	private String message;
	private boolean status;
}
