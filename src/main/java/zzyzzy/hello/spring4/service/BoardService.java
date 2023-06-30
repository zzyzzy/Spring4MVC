package zzyzzy.hello.spring4.service;

import zzyzzy.hello.spring4.model.Board;

import java.util.List;

public interface BoardService {

    List<Board> readBoard(int cpg);

}
