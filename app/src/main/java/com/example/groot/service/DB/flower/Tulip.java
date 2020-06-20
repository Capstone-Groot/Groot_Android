package com.example.groot.service.DB.flower;

import com.example.groot.R;
import com.example.groot.service.DB.FlowerInfo;

import java.util.ArrayList;
import java.util.List;

public class Tulip extends FlowerInfo {
    public Tulip() {
        super("튤립", "내한성 구근초로 가을에 심는다. 비늘줄기는 난형이고 원줄기는 곧게 서며 갈라지지 않는다. 잎은 밑에서부터 서로 연속하여 어긋나고 밑부분은 원줄기를 감싼다. 잎은 길이 20-30cm로 넓은 피침형 또는 타원상 피침형이고 가장자리는 물결 모양이며 안쪽으로 다소 말린다. 또 잎의 빛깔은 청록색 바탕에 흰빛이 돌지만 뒷면은 짙다.\n" +
                "\n" +
                "꽃은 4-5월에 1개씩 위를 향하여 피고 길이 7cm 정도로 넓은 종 모양이다. 꽃받침은 위로 다소 퍼지지만 옆으로는 퍼지지 않으며 수술은 6개이고 암술은 2cm 정도로서 녹색을 띠고 원주형이다. 튤립에는 여러 가지 계통이 있는데 대표적인 것에는 하이브리드 다윈계 등이 있으며, 다윈계와 하이브릿 다윈계가 중요시된다.\n",
                "빨간색 - 사랑의 고백(영원한 사랑)\n" +
                        "보라색 - 영원한 사랑, 영원하지 않은 사랑\n" +
                        "노란색 - 헛된 사랑 혹은 사랑의 표시(혼자하는 사랑)\n" +
                        "흰색 - 실연, 용서, 사과\n" +
                        "검은색 - 당신을 저주합니다\n",
                "학명: Tulipa\n" +
                        "계: 식물계\n" +
                        "문: 속씨식물문\n" +
                        "강: 외떡잎식물강\n" +
                        "목: 백합목\n" +
                        "과: 백합과 \n" +
                        "속: 산자고속\n" +
                        "종: 튤립\n");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.tulip1);
        list.add(R.drawable.tulip2);
        list.add(R.drawable.tulip3);
        list.add(R.drawable.tulip4);
        list.add(R.drawable.tulip5);
        this.insertImage(list);
        return super.getImageList();
    }
}
