package com.example.groot.service.DB.flower;

import com.example.groot.R;
import com.example.groot.service.DB.FlowerInfo;

import java.util.ArrayList;
import java.util.List;


public class Daisy extends FlowerInfo {

    public Daisy() {
        super("데이지", "유럽 서부지역이 원산으로 원종은 한국에 흔히 자생하는 민들레꽃과 비슷하게 생겼다. 잎은 긴 타원형으로 주걱처럼 생겼으며, 키가 작고 로젯트형으로 자란다. 현재 시판되고 있는 데이지는 샤스타 데이지, 달버그데이지, 글로리오사 데이지, 캐이프 데이지, 아프리칸 데이지, 잉글리쉬 데이지 등이 있으며, 보통 데이지라고 하면 잉글리쉬데이지를 말한다. 원산지에서는 여러해살이풀이지만 화훼적으로는 일년생으로 개량되어 나오고 있고 씨앗번식이 잘 되어 가을에 뿌리는 추파일년초로 재배한다. 속명 Bellis는 라틴어의 아름답다는 Bellus에서 유래 되었으며 원산지는 유럽 및 지중해 연안으로 10~15종이 있다. 한국의 봄을 장식하는 대표적인 초화류에 속한다.", "사랑스러움 / 숨겨진 사랑 / 겸손한 아름다움", "학명: Bellis perennis\n" +
                "계: 식물계\n" +
                "문: 속씨식물문\n" +
                "강: 쌍떡잎식물강\n" +
                "목: 초롱꽃목\n" +
                "과: 국화과\n" +
                "속: 데이지속\n" +
                "종: 데이지\n");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.daisy1);
        list.add(R.drawable.daisy2);
        list.add(R.drawable.daisy3);
        list.add(R.drawable.daisy4);
        list.add(R.drawable.daisy5);
        this.insertImage(list);
        return super.getImageList();
    }
}
