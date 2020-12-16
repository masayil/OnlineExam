package com.bean.pagination;

import com.bean.entity.QuestionBank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
    public static int OnePageNum=2;
    private int curPage;
    private int totalpage;
    private ArrayList<QuestionBank> questionBankList;
}
