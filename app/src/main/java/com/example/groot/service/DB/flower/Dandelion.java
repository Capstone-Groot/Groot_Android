package com.example.groot.service.DB.flower;

import com.example.groot.R;
import com.example.groot.service.DB.FlowerInfo;

import java.util.ArrayList;
import java.util.List;

public class Dandelion extends FlowerInfo {
    public Dandelion() {
        super("민들레", "‘앉은뱅이’라는 별명이 있으며, 한국 각처에서 나는 식물로 줄기가 있고 밑동잎이 심장형으로 나온다. 이른봄에 깃털모양으로 갈라진 잎은 뿌리에서 모여나며 구두주걱 모양의 긴 타원형이다. 갈라진 조각은 삼각형이며 끝이 날카롭고, 위쪽은 이빨 모양의 톱니가 있으며 꽃줄기는 약 30 센티미터이다. 꽃은 황색으로 4~5월 또는 10월에 핀다. 두상꽃차례를 이루며 노랑색이고 주로 봄에 핀다. 꽃 필 때에는 흰털이 있으나 나중에는 거의 없어지고 두상꽃차례 밑에만 흰털이 남는다. 두화는 1개가 나며 총포는 종 모양이고, 포편은 2열이며 내편은 줄모양 혹은 바소꼴로 길다. 외편은 긴 타원형으로 끝에 작은 뿔이 난다. 제일 바깥층의 포편은 뒤로 말리지 않는다.\n" +
                "\n" +
                "잔꽃은 혓바닥 모양이고 5개의 톱니가 있다. 수술은 5개이고, 수과는 사각뿔 모양이며 열매 표면의 가운데 위쪽으로 가시처럼 뾰족한 돌기가 있다. 뿌리는 실 모양이고 열매의 2~3배의 길이이며 위쪽이 백색이고 갓털은 가는 털 모양이다. 열매에 흰털이 나 있어 열매를 멀리 운반한다.\n" +
                "\n" +
                "민들레는 겨울에 줄기는 죽지만 이듬해 다시 살아나는 강한 생명력을 지니고 있는 것이 마치 밟아도 다시 꿋꿋하게 일어나는 백성과 같다고 하여 민초(民草)로 비유되기도 한다. 또한 산과 들에 흔히 피는 다년생이다. 최근에는 유럽 원산인 서양민들레도 주위에서 흔히 볼 수 있다.\n",
                "감사하는 마음 / 내 사랑 그대에게 드려요. ",
                "학명: Taraxacum platycarpum \n" +
                        "계: 식물계\n" +
                        "문: 속씨식물문\n" +
                        "강: 쌍떡잎식물강\n" +
                        "목: 초롱꽃목\n" +
                        "과: 국화과\n" +
                        "속: 민들레속\n" +
                        "종: 민들레\n");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.dandelion1);
        list.add(R.drawable.dandelion2);
        list.add(R.drawable.dandelion3);
        list.add(R.drawable.dandelion4);
        list.add(R.drawable.dandelion5);
        this.insertImage(list);
        return super.getImageList();
    }
}
