package com.vitnberg.portopt.portfolio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AssetUniverse {
    private final List<Asset> assets;
    private final Map<Asset, Integer> index;

    public AssetUniverse(List<Asset> assets) {
        this.assets = List.copyOf(assets);
        index = new HashMap<>();
        for (int i = 0; i < assets.size(); i++) {
            index.put(assets.get(i), i);
        }
    }

    public int indexOf(Asset asset) {
        if (!index.containsKey(asset)) {
            return -1;
        }
        return index.get(asset);
    }

    public List<Asset> assets() {
        return assets;
    }

    public int size() {
        return assets.size();
    }

}

