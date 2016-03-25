package com.dd.morphingbutton.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.dd.morphingbutton.MorphingButton;

public class Sample1Activity extends BaseActivity {

    private int mMorphCounter1 = 1;
    private int mMorphCounter2 = 1;

    public static void startThisActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, Sample1Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sample_morph);

        ComboView comboView = (ComboView) findViewById(R.id.combo);
        ComboView.Params params = ComboView.Params.create()
                .color(color(R.color.mb_blue), color(R.color.mb_blue))
                .colorPressed(color(R.color.mb_blue_dark), color(R.color.mb_blue_dark))

                .cornerRadius(dimen(R.dimen.mb_corner_radius_2), dimen(R.dimen.mb_dimen_52))// 后三个值必须相同才能是圆
                .width(dimen(R.dimen.mb_dimen_70), dimen(R.dimen.mb_dimen_52))
                .height(dimen(R.dimen.mb_dimen_38), dimen(R.dimen.mb_dimen_52))

                .strokeWidth(dimen(R.dimen.mb_corner_radius_1),dimen(R.dimen.mb_corner_radius_1))
                .strokeColor(color(R.color.mb_blue),color(R.color.mb_blue))
                .duration(400)
                .padding(dimen(R.dimen.mb_corner_radius_3))
                .text("送礼", "连击")
                .textSize(16)
                .textColor(color(R.color.mb_text_color_white));

        comboView.settingMorphParams(params);

        final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btnMorph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMorphButton1Clicked(btnMorph1);
            }
        });

        final MorphingButton btnMorph2 = (MorphingButton) findViewById(R.id.btnMorph2);
        btnMorph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMorphButton2Clicked(btnMorph2);
            }
        });

        morphToSquare(btnMorph1, 0);
        morphToFailure(btnMorph2, 0);
    }

    private void onMorphButton1Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter1 == 0) {
            mMorphCounter1++;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        } else if (mMorphCounter1 == 1) {
            mMorphCounter1 = 0;
            morphToSuccess(btnMorph);
        }
    }

    private void onMorphButton2Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter2 == 0) {
            mMorphCounter2++;
            morphToFailure(btnMorph,  integer(R.integer.mb_animation));
        } else if (mMorphCounter2 == 1) {
            mMorphCounter2 = 0;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        }
    }

    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(dimen(R.dimen.mb_width_200))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_blue))
                .colorPressed(color(R.color.mb_blue_dark))
                .text(getString(R.string.mb_button));
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(integer(R.integer.mb_animation))
                .cornerRadius(dimen(R.dimen.mb_height_70))
                .width(dimen(R.dimen.mb_height_70))
                .height(dimen(R.dimen.mb_height_70))
                .color(color(R.color.mb_green))
                .colorPressed(color(R.color.mb_green_dark))
                .text("Done");
        btnMorph.morph(circle);
    }

    private void morphToFailure(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_height_56))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_red))
                .colorPressed(color(R.color.mb_red_dark))
                .icon(R.drawable.ic_lock);
        btnMorph.morph(circle);
    }

}
