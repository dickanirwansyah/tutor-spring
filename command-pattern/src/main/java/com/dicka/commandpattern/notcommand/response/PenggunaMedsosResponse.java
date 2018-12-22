package com.dicka.commandpattern.notcommand.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenggunaMedsosResponse<T> {

    private T data1;
    private List data2;

}
