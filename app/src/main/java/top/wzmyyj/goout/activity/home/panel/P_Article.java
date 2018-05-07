package top.wzmyyj.goout.activity.home.panel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import top.wzmyyj.goout.R;
import top.wzmyyj.goout.base.BaseNestedScrollPanel;
import top.wzmyyj.goout.bean.Article;
import top.wzmyyj.goout.database.ArticleData;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public class P_Article extends BaseNestedScrollPanel {
    public P_Article(Context context) {
        super(context);
        this.title = "文章";
    }

    private int aid = -1;
    private Article article;

    @Override
    public void initSome(Bundle savedInstanceState) {
        Intent i = activity.getIntent();
        aid = i.getIntExtra("aid", -1);
        if (aid == -1) {
            activity.finish();
        }
        article = ArticleData.getArticle(aid);
        if (article == null) {
            activity.finish();
        }
        super.initSome(savedInstanceState);
    }

    @Override
    protected void setView(NestedScrollView ns, SwipeRefreshLayout srl, FrameLayout layout) {

    }


    private TextView tv_title;
    private ImageView img_head;
    private TextView tv_name;
    private Button bt_add;
    private TextView tv_text;

    @Override
    protected View getContentView() {
        View content = mInflater.inflate(R.layout.activity_article, null);
        tv_title = content.findViewById(R.id.tv_title);
        img_head = content.findViewById(R.id.img_head);
        tv_name = content.findViewById(R.id.tv_name);
        bt_add = content.findViewById(R.id.bt_add);
        tv_text = content.findViewById(R.id.tv_text);
        contentData();
        contentListener();
        return content;
    }

    private void contentData() {
        tv_title.setText(article.getTitle());
        img_head.setImageResource(article.getHead());
        tv_name.setText(article.getName());
        tv_text.setText(article.getTitle() + s);

    }

    private void contentListener() {
    }


    private String s = "户外拓展训练是指通过专业的机构，对现在久居城市的人进行的一种野外生 \n" +
            "\n" +
            "存训练，但是基本上只能学到一些常识的东西，并不能学习到真正的户外生存知 \n" +
            "\n" +
            "识，更多的是通过一种野外的生存环境来刺激原本麻木的精神，从而达到团队合 \n" +
            "\n" +
            "作的目的。让人更加珍惜现在的生活。 \n" +
            "\n" +
            "   户外拓展训练实践的目的： \n" +
            "\n" +
            "1、综合活动性。 \n" +
            "\n" +
            "     户外拓展训练的所有项目都以体能活动为引导，引发出认知活动、情感活动、 \n" +
            "\n" +
            "意志活动和交往活动，有明确的操作过程，要求学员全身心的投入。 \n" +
            "\n" +
            "2、挑战极限。 \n" +
            "\n" +
            "     拓户外展训练的项目都具有一定的难度，表现在心理考验上，需要学员向自 \n" +
            "\n" +
            "己的能力极限挑战，跨越“极限”。 \n" +
            "\n" +
            "3、集体中的个性。 \n" +
            "\n" +
            "     户外拓展训练实行分组活动，强调集体合作。力图使 野外拓展训练 每一名 \n" +
            "\n" +
            "学员竭尽全力为集体争取荣誉，同时从集体中吸取巨大的力量和信心，在集体中 \n" +
            "\n" +
            "显示个性。 \n" +
            "\n" +
            "4、高峰体验。 \n" +
            "\n" +
            "     在克服困难，顺利完成课程要求以后，学员能够体会到发自内心的胜利感和 \n" +
            "\n" +
            "自豪感，获得人生难得的高峰体验。 \n" +
            "\n" +
            "  5、自我教育。 \n" +
            "\n" +
            "     教员只是在课前把课程的内容、目的、要求以及必要的安全注意事项向学员 \n" +
            "\n" +
            "讲清楚，活动中一般不进行讲述，也不参与讨论，充分尊重学员的主体地位和主 \n" +
            "\n" +
            "观能动性。即使在课后的总结中，教员只是点到为止，主要让学员自己来讲，达 \n" +
            "\n" +
            "到了自我教育的目的。 \n" +
            "\n" +
            "     通过户外拓展训练，参训者在如下方面有显著的提高：认识自身潜能，增强 \n" +
            "\n" +
            "自信心，改善自身形象；克服心理惰性，磨练战胜困难的毅力；启发想象力与创 \n" +
            "\n" +
            "造力，提高解决问题的能力；认识群体的作用，增进对集体的参与意识与责任心； \n" +
            "\n" +
            "改善人际关系，学会关心，更为融洽地与群体合作；学习欣赏、关注和爱护大自 \n" +
            "\n" +
            "然。 \n" +
            "\n" +
            "户外拓展训练的意义： \n" +
            "\n" +
            "1、个人心理训练 \n" +
            "\n" +
            "     户外拓展训练是一项旨在协助企业提升员工核心价值的训练过程，通过训练 \n" +
            "\n" +
            "课程能够有效地拓展企业人员的潜能，提升和强化个人心理素质，帮助企业人员 \n" +
            "\n" +
            "建立高尚而尊严的人格；同时让团队成员能更深刻地体验个人与企业之间，下级 \n" +
            "\n" +
            "与上级之间，员工与员工之间唇齿相依的关系，从而激发出团队更高昂的工作热 \n" +
            "\n" +
            "诚和拼搏创新的动力，使团队更富凝聚力。 \n" +
            "\n" +
            "2 、团队合作训练 \n" +
            "\n" +
            "     户外拓展训练是一套塑造团队活力、推动组织成长的不断增值的训练课程。 \n" +
            "\n" +
            "是专门配合现代企业进行团队建设需要而设计的一套户外体验式模拟训练，这是 \n" +
            "\n" +
            "当今欧洲、美洲及亚洲大型商业机构所采纳的一种有效的训练模式；训练内容丰 \n" +
            "\n" +
            "富生动，寓意深刻，以体验启发作为教育手段，学员参与的训练将成为他们终身 \n" +
            "\n" +
            "难忘的经历，从而让每一系列活动中所寓意的深刻的道理和观念，能牢牢地扎根 \n" +
            "\n" +
            "在团队和每个成员的潜意识中，并且能在日后的工作合作中挥发应有的效用。通 \n" +
            "\n" +
            "过拓展训练，学员在以下方面将有显著的提高：认识自身潜能，增强自信心，改 \n" +
            "\n" +
            "善自身形象；克服心理惰性，磨练战胜困难的毅力；启发想象力与创造力，提高 \n" +
            "\n" +
            "解决问题的能力；认识群体的作用，增进对集体的参与意识与责任心；改善人际 \n" +
            "\n" +
            "关系，更为融洽地与群体合作；学习欣赏、关注和爱护自然。 \n" +
            "\n" +
            "3、现实社会意义 \n" +
            "\n" +
            "     现代社会是一个高度人际互动的社会，是一个团队英雄主义的时代。如何实 \n" +
            "\n" +
            "现团队的整体优势和优势互补？在这个生活节奏越来越快，工作分工越来越细， \n" +
            "\n" +
            "工作压力越来越大，人与人的情感交流越来越困难的竞争环境中，企业、组织和 \n" +
            "\n" +
            "个人更需要团队。户外拓展训练揉合了高挑战及低挑战的元素，学员从中在个人 \n" +
            "\n" +
            "和团队的层面，都可透过危机感、领导、沟通、面对逆境和辅导的培训而得到提 \n" +
            "\n" +
            "升。拓展训练强调学员去“感受”学习，而不仅仅在课堂上听讲。 \n" +
            "拓展训练的意义\n" +
            "拓展训练并非体育加娱乐，也不是所谓的\"魔鬼训练\"；它回答" +
            "我们这样一个问题：知识和技能只是有形的资本，而强烈的进取心、" +
            "顽强的拼搏精神与意志力、良好的沟通与合作精神，才更是一种无形的" +
            "力量。在什么样的情况下能使有限的知识和技能释放出极大的能量；" +
            "如何开发出那些一直潜伏在每个人身上、而人们又未必真正了解的" +
            "能力和情趣；怎样才能实现与他人的良好沟通和弄清这种沟通能够" +
            "深入到什么程度；怎样有效地破除个人自我中心概念，改变对于他" +
            "人和社会的冷漠心态……这就是拓展训练的真正意义所在。\n" +
            "    拓展训练强调体验式学习（Experiential Learning），" +
            "至今已发展成为培养现代人和熔炼现代组织的一种全新的学习方" +
            "法和训练方式。它以合作意识、进取精神的激发和升华为宗旨，" +
            "利用户外特殊的场地和崇山峻岭等自然环境，配合各种精心设计" +
            "的各种\"挑战极限\"性质的活动和团队游戏，使参与者更深入探" +
            "索自我，挖掘自我潜能，体会个人与团队的关系，突破自己的固" +
            "有模式，学习如何面对恐惧和困难，确立信心，培养团队合作精神，" +
            "增强团队活力、创造性和凝聚力，提升团队生产力，提高团队绩效" +
            "，巩固客户关系，从而达到提升企业的整体竞争力的目的。\n" +
            "在拓展中展现自我，在生活中，我们往往要做出许多抉择，有些抉" +
            "择需要的是勇气。在事业上，我们往往去面对很多机遇，把握机遇" +
            "需要的是决心。越出窘境后的海阔天空是令人向往的，但是否有" +
            "勇气和决心迈出至关重要的一步是最关键的。经历这一切之后，" +
            "你会发觉人的潜能是巨大的。 \n" +
            "    拓展训练，是一种对生活的感悟，一种对艰辛的体验。参加" +
            "拓展训练时，从学员们做项目之前的组队到做项目时的同生死、" +
            "共患难的拼搏精神，能真正体会到个人与个人，团队与团队，" +
            "个人与团队，这之间的关系。拓展训练更多的是对团队合作精神" +
            "的体现，对个人心理的挑战，让人直面真实的自我，体会团队的力量。 \n" +
            "    现代社会的竞争中，并非你死我活，而应该是“你活我也活”" +
            "，大家应共同发展，一种合作共赢的局面。拓展是在告知你我，" +
            "在现实社会中，“天马行空，独来独往”是不可能的，成功需要合作。" +
            "没有完美的个人，只有完美的团队，要想卓越，只有合作。其实，" +
            "团队合作中最主要的是个人心理素质的表现，自己的能力能否正常发挥，" +
            "并不是大胆的行为，而是一种认知行为。未来有不可预知的情境，" +
            "用什么样的心态去面对是最重要的，拓展只是带给你一个认识自己、" +
            "挖掘自己的机会；让自己清晰的认识到如何更好的融入到团队工作中。 \n" +
            "拓展，更是一种对自我素质的再提高，一种推动企业整体发展的动力。" +
            "经历过拓展训练，将自己的真实能力展现在工作岗位中，是对自我价值" +
            "的肯定，也是对团队发展的促进。\n" +
            "企业的进步依托的不是个人英雄主义，而是高度互动、高度合作的组织；" +
            "现代企业的竞争，从某种程度来说是企业整合能力的竞争，是员工队伍整" +
            "体素质的竞争，拥有一支积极进取团结合作的员工队伍是企业取胜的法宝。\n" +
            "“多一点勇气与自信、多一点理解与沟通、多一点进取与互助”，在紧张的" +
            "工作之余拓拔进取、展现自我。拓展培训是以游戏为载体，以运动为依托，" +
            "以培训为方式，以感悟为目的。它与传统的知识培训和技能培训相比，少了" +
            "一些说教和灌输，多了一些运动中的体验和感悟，在青山绿水之间，通过" +
            "趣味性的游戏、启发式的运动、相互依存的环境条件和导师的精短点评，" +
            "促进人与人之间的沟通和信任。在培训的过程中把企业文化理念与各项活动" +
            "项目有机融合，让参训者通过活动培养积极的心态、重审自我、挖掘自身潜力" +
            "，增强协作意识，从而提高公司整体凝聚力，激发员工的工作激情。";

}
