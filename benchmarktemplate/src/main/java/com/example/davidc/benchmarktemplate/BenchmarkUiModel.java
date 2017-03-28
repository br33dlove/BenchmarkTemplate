package com.example.davidc.benchmarktemplate;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.davidc.uiwrapper.UiModel;

class BenchmarkUiModel implements UiModel<BenchmarkUi> {
    enum ButtonState {START_BENCHMARKING, LOADING}
    private ButtonState buttonState;
    private String results;
    private String error;

    BenchmarkUiModel(final ButtonState buttonState, final String results, final String error) {
        this.buttonState = buttonState;
        this.results = results;
    }

    private BenchmarkUiModel(final Parcel parcel) {
        buttonState = (ButtonState) parcel.readSerializable();
        results = parcel.readString();
        error = parcel.readString();
    }

    @Override
    public void onto(BenchmarkUi ui) {
        if (results != null && !results.isEmpty()) {
            ui.showBenchmarkText(results);
        } else if (error != null && !error.isEmpty()) {
            ui.showBenchmarkText(error);
        }
        switch (buttonState) {
            case START_BENCHMARKING: {
                ui.showStartBenchmarking();
                break;
            }
            case LOADING: {
                ui.showStartBenchmarking();
                break;
            }
        }
    }

    void showStartBenchmarking(final BenchmarkUi ui) {
        buttonState = ButtonState.START_BENCHMARKING;
        if (ui != null) {
            ui.showStartBenchmarking();
        }
    }

    void showLoadingBenchmarks(final BenchmarkUi ui) {
        buttonState = ButtonState.LOADING;
        if (ui != null) {
            ui.showLoadingBenchmarks();
        }
    }

    void showBenchmarks(final BenchmarkUi ui, final String results) {
        error = "";
        this.results = results;
        if (ui != null) {
            ui.showBenchmarkText(results);
        }
    }

    void showError(final BenchmarkUi ui, final String error) {
        results = "";
        this.error = error;
        if (ui != null) {
            ui.showBenchmarkText(error);
        }
    }



    boolean isInLoadingState() {
        return buttonState == ButtonState.LOADING;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(buttonState);
        dest.writeString(results);
        dest.writeString(error);
    }

    final static Parcelable.Creator<BenchmarkUiModel> CREATOR = new Parcelable.Creator<BenchmarkUiModel>() {
        @Override
        public BenchmarkUiModel createFromParcel(Parcel source) {
            return new BenchmarkUiModel(source);
        }

        @Override
        public BenchmarkUiModel[] newArray(int size) {
            return new BenchmarkUiModel[size];
        }
    };
}
