package com.centaury.cataloguemovie.utils;

import com.centaury.cataloguemovie.data.MovieEntity;
import com.centaury.cataloguemovie.data.TVShowEntity;

import java.util.ArrayList;

/**
 * Created by Centaury on 10/6/2019.
 */
public class FakeDataDummy {

    public static ArrayList<MovieEntity> generateDummyMovies() {

        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieEntity(1,
                "Joker",
                "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "2019-10-04",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "8.8"));
        movieEntities.add(new MovieEntity(2,
                "Spider-Man: Far from Home",
                "https://image.tmdb.org/t/p/w500/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "2019-07-02",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "7.7"));
        movieEntities.add(new MovieEntity(3,
                "The Old Man & the Gun",
                "https://image.tmdb.org/t/p/w500/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
                "2018-09-28",
                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
                "6.3"));
        movieEntities.add(new MovieEntity(4,
                "It Chapter Two",
                "https://image.tmdb.org/t/p/w500/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
                "2019-09-06",
                "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.",
                "7.1"));
        movieEntities.add(new MovieEntity(5,
                "Rambo: Last Blood",
                "https://image.tmdb.org/t/p/w500/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg",
                "2019-09-20",
                "When John Rambo's niece travels to Mexico to find the father that abandoned her and her mother, she finds herself in the grasps of Calle Mexican sex traffickers. When she doesn't return home as expected, John learns she's crossed into Mexico and sets out to get her back and make them pay.",
                "6.2"));
        movieEntities.add(new MovieEntity(6,
                "Toy Story 4",
                "https://image.tmdb.org/t/p/w500/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                "2019-06-21",
                "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                "7.6"));
        movieEntities.add(new MovieEntity(7,
                "Fast & Furious Presents: Hobbs & Shaw",
                "https://image.tmdb.org/t/p/w500/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                "2019-08-02",
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "6.5"));
        movieEntities.add(new MovieEntity(8,
                "John Wick: Chapter 3 - Parabellum",
                "https://image.tmdb.org/t/p/w500/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg",
                "2019-05-17",
                "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
                "7.1"));
        movieEntities.add(new MovieEntity(9,
                "Aladdin",
                "https://image.tmdb.org/t/p/w500/3iYQTLGoy7QnjcUYRJy4YrAgGvp.jpg",
                "2019-05-24",
                "A kindhearted street urchin named Aladdin embarks on a magical adventure after finding a lamp that releases a wisecracking genie while a power-hungry Grand Vizier vies for the same lamp that has the power to make their deepest wishes come true.",
                "7.1"));
        movieEntities.add(new MovieEntity(10,
                "Cars",
                "https://image.tmdb.org/t/p/w500/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg",
                "2006-06-09",
                "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.",
                "6.7"));

        return movieEntities;
    }

    public static ArrayList<TVShowEntity> generateDummyTVShows() {

        ArrayList<TVShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TVShowEntity(1,
                "The Simpsons",
                "https://image.tmdb.org/t/p/w500/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "1989-12-17",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "7.1"));
        tvShowEntities.add(new TVShowEntity(2,
                "The Flash",
                "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "6.7"));
        tvShowEntities.add(new TVShowEntity(3,
                "Arrow",
                "https://image.tmdb.org/t/p/w500/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "5.8"));
        tvShowEntities.add(new TVShowEntity(4,
                "Family Guy",
                "https://image.tmdb.org/t/p/w500/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
                "1999-01-31",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "6.5"));
        tvShowEntities.add(new TVShowEntity(5,
                "Grey's Anatomy",
                "https://image.tmdb.org/t/p/w500/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",
                "2005-03-27",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "6.4"));
        tvShowEntities.add(new TVShowEntity(6,
                "Fear the Walking Dead",
                "https://image.tmdb.org/t/p/w500/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
                "2015-08-23",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "6.3"));
        tvShowEntities.add(new TVShowEntity(7,
                "Supernatural",
                "https://image.tmdb.org/t/p/w500/3iFm6Kz7iYoFaEcj4fLyZHAmTQA.jpg",
                "2005-09-13",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                "7.3"));
        tvShowEntities.add(new TVShowEntity(8,
                "The Seven Deadly Sins",
                "https://image.tmdb.org/t/p/w500/gxTojpKEOtue85EEFlozwRbDXwJ.jpg",
                "2014-10-05",
                "The “Seven Deadly Sins”—a group of evil knights who conspired to overthrow the kingdom of Britannia—were said to have been eradicated by the Holy Knights, although some claim that they still live. Ten years later, the Holy Knights have staged a Coup d'état and assassinated the king, becoming the new, tyrannical rulers of the kingdom. Elizabeth, the king's only daughter, sets out on a journey to find the “Seven Deadly Sins,” and to enlist their help in taking back the kingdom.",
                "7.9"));
        tvShowEntities.add(new TVShowEntity(9,
                "The Blacklist",
                "https://image.tmdb.org/t/p/w500/bgbQCW4fE9b6wSOSC6Fb4FfVzsW.jpg",
                "2013-09-23",
                "Raymond \\\"Red\\\" Reddington, one of the FBI's most wanted fugitives, surrenders in person at FBI Headquarters in Washington, D.C. He claims that he and the FBI have the same interests: bringing down dangerous criminals and terrorists. In the last two decades, he's made a list of criminals and terrorists that matter the most but the FBI cannot find because it does not know they exist. Reddington calls this \\\"The Blacklist\\\". Reddington will co-operate, but insists that he will speak only to Elizabeth Keen, a rookie FBI profiler.",
                "7"));
        tvShowEntities.add(new TVShowEntity(10,
                "Marvel's Agents of S.H.I.E.L.D.",
                "https://image.tmdb.org/t/p/w500/iWopHyAvuIDjGX10Yc3nn6UEebW.jpg",
                "2013-09-24",
                "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                "6.8"));

        return tvShowEntities;
    }
}
