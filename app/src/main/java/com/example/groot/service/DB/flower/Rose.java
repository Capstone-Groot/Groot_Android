package com.example.groot.service.DB.flower;

import com.example.groot.R;
import com.example.groot.service.DB.FlowerInfo;

import java.util.ArrayList;
import java.util.List;

public class Rose extends FlowerInfo {
    public Rose() {
        super("장미", "장미의 잎은 어긋나기를 하고 보통 홀수 깃꼴겹잎을 이루지만 홑잎인 것도 있으며, 턱잎이 있다.\n" +
                "\n" +
                "꽃은 줄기 끝에 단생꽃차례나 산방꽃차례로 피며, 홑꽃은 꽃잎이 5개지만 원예종 가운데에는 홑꽃 이외에 겹꽃·반겹꽃을 이루는 것이 많다.\n" +
                "\n" +
                "장미의 대표적 특성 중 하나는 가시인데, 가시는 줄기의 표피세포가 변해서 끝이 날카로운 구조로 변한 것이다.\n" +
                "\n" +
                "원예종은 거의 세계 모든 나라에서 재배되고 있다. 한국에는 19세기 후반, 미국·유럽으로부터 서양장미가 들어와 다채로운 원예종의 장미를 재배·관상할 수 있게 되었다. 장미의 잎은 초식성 벌인 장미등에잎벌 애벌레의 먹이식물인데, 애벌레는 무리지어 장미의 잎을 먹는다.\n",
                "붉은 장미 – 사랑 / 아름다움 / 낭만적인 사랑 / 용기 / 존경 / 열망 / 열정\n" +
                        "하얀 장미 - 순수 / 결백 / 젊음 / 영성 / 숭배 / 새로운 시작\n" +
                        "분홍색 장미 - 감탄 / 감사 / 성실 / 우아함\n" +
                        "보라색 장미 - 불완전한 사랑 / 영원한 사랑\n" +
                        "파란 장미 - 이뤄질 수 없는 사랑 / 불가능한 것 → 기적 / 포기하지 않는 사랑\n",
                "학명: Rosa\n" +
                        "계: 식물계\n" +
                        "문: 속씨식물문\n" +
                        "강: 쌍떡잎식물강\n" +
                        "목: 장미목\n" +
                        "과: 장미과 \n" +
                        "속: 장미속\n" +
                        "종: 장미\n");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.rose1);
        list.add(R.drawable.rose2);
        list.add(R.drawable.rose3);
        list.add(R.drawable.rose4);
        list.add(R.drawable.rose5);
        this.insertImage(list);
        return super.getImageList();
    }
}
