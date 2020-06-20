package com.example.groot.service.DB.flower;

import com.example.groot.R;
import com.example.groot.service.DB.FlowerInfo;

import java.util.ArrayList;
import java.util.List;

public class SunFlower extends FlowerInfo {
    public SunFlower() {
        super("해바라기",
                "최대 4.6m까지 자라는데 한국에서는 3m까지 자랄 수 있다.\n" +
                        "\n" +
                        "커다란 잎은 달걀 모양이고 가장자리에 톱니가 있으며, 줄기에 서로 어긋나게 달린다.\n" +
                        "\n" +
                        "꽃의 지름은 30cm까지 자란다. 원산지에서는 최대 4 ~ 8m까지 자라고 꽃 크기도 매우 커서 최대 지름 60cm에서 큰종은 좀 더 크다. 옆으로 가지를 치지 않고 다 크면 위에 매우 큰 꽃을 피운다. 꽃은 두상꽃차례를 이루면서 피는데 한 그루에 두상꽃차례가 한 개 이상씩 달린다. 꽃차례는 노란색을 띠는 큰 혀꽃과 이를 둘러싸는 작은 관꽃으로 이루어져 있다. 꽃차례는 보통 지름이 30cm를 넘으며, 씨를 1,000개 정도 맺는다. 하루 종일 해를 향해 피는 해바라기꽃에는 꿀이 풍부하므로 벌이 많이 날아온다. 꽃은 8~9월에 핀다.\n", "숭배 / 기다림 / 당신만을 사랑합니다. ", "학명: Helianthus annuus\n" +
                        "계: 식물계\n" +
                        "문: 속씨식물문\n" +
                        "강: 진정쌍떡잎식물강\n" +
                        "목: 국화목\n" +
                        "과: 국화과 \n" +
                        "속: 해바라기속\n" +
                        "종: 해바라기\n");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.sunflower1);
        list.add(R.drawable.sunflower2);
        list.add(R.drawable.sunflower3);
        list.add(R.drawable.sunflower4);
        list.add(R.drawable.sunflower5);
        this.insertImage(list);
        return super.getImageList();
    }

}
