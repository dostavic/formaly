#include "greatest-master/greatest.h"
#include "zadanie.h"

TEST string_is_good(){
    ASSERT_EQ(1, isAccepted("a"));
    PASS();
}

TEST string_is_bad(){
    ASSERT_EQ(-1, isAccepted("abb"));
    PASS();
}

TEST string_is_bad_1(){
    ASSERT_EQ(-1, isAccepted("aa"));
    PASS();
}

TEST string_is_good_1(){
    ASSERT_EQ(1, isAccepted("bcccaca"));
    PASS();
}

TEST string_is_good_2(){
    ASSERT_EQ(1, isAccepted("bcccbc"));
    PASS();
}

TEST string_is_good_3(){
    ASSERT_EQ(1, isAccepted("bbca"));
    PASS();
}

TEST string_is_good_4(){
    ASSERT_EQ(1, isAccepted("bbc"));
    PASS();
}

TEST string_is_bad_2(){
    ASSERT_EQ(-1, isAccepted("bccba"));
    PASS();
}

TEST string_is_bad_3(){
    ASSERT_EQ(-1, isAccepted("bcccca"));
    PASS();
}

TEST string_is_bad_4(){
    ASSERT_EQ(-1, isAccepted("accaca"));
    PASS();
}

TEST string_is_bad_5(){
    ASSERT_EQ(-1, isAccepted("ccccbca"));
    PASS();
}

TEST string_is_bad_6(){
    ASSERT_EQ(-1, isAccepted("accccbca"));
    PASS();
}

GREATEST_MAIN_DEFS();

int main(int argc, char** argv){
    GREATEST_MAIN_BEGIN();

    RUN_TEST(string_is_good);
    RUN_TEST(string_is_bad);
    RUN_TEST(string_is_bad_1);
    RUN_TEST(string_is_good_1);
    RUN_TEST(string_is_good_2);
    RUN_TEST(string_is_good_3);
    RUN_TEST(string_is_good_4);
    RUN_TEST(string_is_bad_2);
    RUN_TEST(string_is_bad_3);
    RUN_TEST(string_is_bad_4);
    RUN_TEST(string_is_bad_5);
    RUN_TEST(string_is_bad_6);

    GREATEST_MAIN_END();
}