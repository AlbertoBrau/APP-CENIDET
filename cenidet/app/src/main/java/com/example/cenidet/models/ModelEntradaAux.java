package com.example.cenidet.models;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class ModelEntradaAux {

    private SwitchMaterial switchB;

    public ModelEntradaAux(SwitchMaterial switchB) {
        this.switchB = switchB;
    }

    public void activarSwitchB(){
        switchB.setChecked(true);
    }

}
