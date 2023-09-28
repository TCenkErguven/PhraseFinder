package com.visteknoloji.utility;

import com.visteknoloji.dto.request.SavePhraseRequestDto;
import com.visteknoloji.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner {
    private final PhraseService phraseService;

    /**
     *     Program ilk ayağa kalktığında defaultta yüklenmesini istediğimiz yazarlar ve yazıları
     */
    @PostConstruct
    public void init(){
        List<SavePhraseRequestDto> phraseList = Arrays.asList(
                SavePhraseRequestDto.builder()
                    .authorPhrase("Zarif bir hüzündür bembeyaz dolaşan kuğuya bakarsak\n" +
                            "Mücevher titreşimleriyle mütereddit bir akşam suya bakarsak\n" +
                            "Fazlasıyla ısındı deniz kaynadı kaynayacak\n" +
                            "Dipten bir deprem yaklaşıyor suyun üzerindeki buğuya bakarsak\n" +
                            "Ne kadar yoksul ve çıplak görünürse görünsün ağaçlar\n" +
                            "O kadar yakındır ilkbahar özsuyu yürümüş dallara uğultuyla bakarsak\n")
                        .authorName("Atilla")
                        .authorSurname("Ilhan")
                        .build(),
                SavePhraseRequestDto.builder()
                        .authorPhrase("Ruhum\n" +
                                "gözlerini yumuşacık yum\n" +
                                "kucağımdaymışsın gibi bırak kendini\n" +
                                "ninni,\n" +
                                "uykunda unutma beni\n" +
                                "ninni…\n" +
                                "Gözlerini yumuşacık yum\n" +
                                "yeşil ela gözlerini\n" +
                                "ninni ruhum ninni\n" +
                                "Sen yukarda yemişli dalların içindesin,\n" +
                                "yeşil gözlerin güneş dolu,\n" +
                                "dudakların bala bulanmış\n" +
                                "ben ağacın dibindeyim,\n" +
                                "bir ayağım çukurda…\n" +
                                "Ben senden çok önce gideceğim,\n" +
                                "sen bensiz kalacaksın ihtiyarlığında…")
                        .authorName("Nazım")
                        .authorSurname("Hikmet")
                        .build(),
                SavePhraseRequestDto.builder()
                        .authorPhrase("Blow, blow, thou winter wind,\n" +
                                "   Thou art not so unkind\n" +
                                "      As man’s ingratitude;\n" +
                                "   Thy tooth is not so keen,\n" +
                                "Because thou art not seen,\n" +
                                "      Although thy breath be rude.\n" +
                                "Heigh-ho! sing, heigh-ho! unto the green holly:\n" +
                                "Most friendship is feigning, most loving mere folly:\n" +
                                "   Then, heigh-ho, the holly!\n" +
                                "      This life is most jolly.\n" +
                                "\n" +
                                "   Freeze, freeze, thou bitter sky,\n" +
                                "   That dost not bite so nigh\n" +
                                "      As benefits forgot:\n" +
                                "   Though thou the waters warp,\n" +
                                "      Thy sting is not so sharp\n" +
                                "      As friend remembered not.")
                                .authorName("William")
                                .authorSurname("SHAKESPEARE")
                                .build(),
                SavePhraseRequestDto.builder()
                        .authorPhrase("hacet yok hatırlatmasına seni hatıraların\n" +
                                "bir dakika bile çıkmıyorsun aklımdan\n" +
                                "koşar gibi yürüyüşün\n" +
                                "karanlıkta bir ışık gibi aydınlık gülüşün\n" +
                                "\n" +
                                "hacet yok hatırlatmasına seni hatıraların\n" +
                                "uzak uzak yıldızlarla çevrilmiş kainatın\n" +
                                "karanlık boşluklarında akıp giderken zaman\n" +
                                "\n" +
                                "adımla nasıl berabersem öylece beraberiz\n" +
                                "seninle her saat seninle her dakika seninle her saniye\n" +
                                "gönlümüz mutluluğa inanmış olmanın gururuyla rahat\n" +
                                "koltuğumuzun altında birer dinamit gibi kellemiz\n" +
                                "ve sonra her zaman her ölümlüye\n" +
                                "aynı şartlar altında kısmet olmıyan\n" +
                                "gerçekleri görmenin aydınlığı alınlarımızda\n" +
                                "\n" +
                                "hacet yok hatırlatmasına seni hatıraların\n" +
                                "sen bana kalbim kadar elim kadar yakınsın")
                        .authorName("Atilla")
                        .authorSurname("Ilhan")
                                .build(),
                SavePhraseRequestDto.builder()
                        .authorPhrase("sevgilim  bir günün ortası şimdi taşıtlar hızla gelip geçiyor  her yer kalabalık ben seni düşünüyorum bir bodrum kahvesinde uzat bana uzat ellerini i̇zinli askerler görüyorum  kırıtarak yürüyen işçi kızlar i̇stanbul her günkü yaşantısı içinde  uğultulu  güvercinler güneşten bir sessizliği biriktiriyor ben seni düşünüyorum seni hani tıpkı o ilk günlerdeki gibi kalbim diyorum kalbim daha dün tezgâhtan çıkmış bir su sayacı gibi aşkı anılar besliyor düşler kadar bu yüzden diyorum aşk eskidikçe aşktırsevgi eskidikçe sevgi  günümüz ekmeğimiz  türkümüz çoluğumuz çocuğumuz binalar yan yana yükselip gidiyorvapurların ağzı köpük içinde uzaklarda kapılar açılıyor trenin biri bir istasyona varıyor ordan çıkıyor biri  her şey biliyor her şey sen biliyor musun bakalım seni nice sevdiğimi  üstüne titrediğimi  geldiğimi  gittiğimi hadi ")
                                .authorName("Cemal")
                                .authorSurname("Süreyya")
                        .build()
        );

        phraseList.stream().forEach(phrase -> {
            try {
                phraseService.savePhrase(phrase);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        });

    }
}
