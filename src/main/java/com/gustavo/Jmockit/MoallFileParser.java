package com.gustavo.Jmockit;


public class MoallFileParser {
    private String path;
    private MoCompartor moCompartor;
    private SyncTaskInfo syncTaskInfo;
    private int type;

    public MoallFileParser(String path, MoCompartor moCompartor, SyncTaskInfo syncTaskInfo) {
        this.path = path;
        this.moCompartor = moCompartor;
        this.syncTaskInfo = syncTaskInfo;
    }

    private boolean isStart() {
        return type == 1;
    }
}
