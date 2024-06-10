package com.believesun.mysqltrain.service;

import com.believesun.mysqltrain.exceptions.ReaderAddErrorException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ReaderManagedService {
    Boolean addReader(HttpServletRequest request, HttpServletResponse response) throws ReaderAddErrorException;

    Boolean editReader(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
