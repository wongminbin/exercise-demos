#include "ftauthng.h"
#include "ftngpskc.h"

#include <string.h>
#include <stdio.h>
#include <time.h>

class OTP {
public:

   virtual ~OTP() {}

   char* version() {
        return get_version();
    }


    /* 令牌激活 */
   int enableToken(const char *pdata_str, const char *pin, const char *otp_serial){
	/* 令牌私有数据结构 */
	StTokenData pdata;

	/* 设置测试令牌私有数据, 实际对应为令牌数据表的TK_PRIVATE_DATA */
      strcpy( pdata.priv_data, pdata_str );

   	int  ret = FT_API_SUCC;

    	/* 先将字符数据转化为结构 */
    	decode_pdata( &pdata );

    	/* 设定用户名, 一般为令牌序列号 */
    	if ( FT_API_SUCC != ( ret = set_user_login( &pdata, otp_serial ) ) )
    	{
        printf( "set_user_login err: %d\n", ret );
        return ret;
    	}
    	printf( "set_user_login ok\n" );

    	/* 设置PIN码 */
    	if ( FT_API_SUCC != (ret = set_pin( &pdata, pin ) ) )
    	{
        printf( "set_pin err: %d\n", ret );
        return ret;
    	}
    	printf( "set_pin ok\n" );

    	/* 启用令牌 */
    	if ( FT_API_SUCC != (ret = enable_token( &pdata ) ) )
    	{
        printf( "enable_token err: %d\n", ret );
        return ret;
    	}
    	printf( "enable_token ok\n" );

    	/* 将令牌结构重新编码 */
    	encode_pdata(&pdata);
    	return ret;
   }


   /* 验证动态密码 */
  int checkCode(const char *pdata_str, const char *pin, const char *code, long tm){
	/* 令牌私有数据结构 */
	StTokenData pdata;

	/* 设置测试令牌私有数据, 实际对应为令牌数据表的TK_PRIVATE_DATA */
      strcpy( pdata.priv_data, pdata_str );
	
	int ret = FT_API_SUCC;

	/* 先将字符数据转化为结构 */
	decode_pdata(&pdata);

	if ( FT_API_SUCC !=
		 ( ret = check_password( tm, // 产生 OTP 的实际时间
		                         &pdata,
		                         code,         // 由 genotp 接口产生的 OTP
		                         pin ) ) )
	 {
		printf( "check_password err: %d\n", ret );
	 }
	 else
	 {
		printf( "check_password ok\n" );
	 }

	 /* 将令牌结构重新编码 */
	 encode_pdata(&pdata);

	 return ret;
   }
   
   /* 产生的 OTP */
  void genCode(const char *pdata_str, long tm){
	/* 令牌私有数据结构 */
	StTokenData pdata;

	/* 设置测试令牌私有数据, 实际对应为令牌数据表的TK_PRIVATE_DATA */
      strcpy( pdata.priv_data, pdata_str );
	
	int ret = FT_API_SUCC;
	char otp[OTP_SIZE];

	/* 先将字符数据转化为结构 */
	decode_pdata(&pdata);
	
	if((ret = genotp(tm, &pdata, otp) ) != FT_API_SUCC) {
		printf( "gen_otp err: %d\n", ret );
	}
	printf(otp);
	/* 将令牌结构重新编码 */
	encode_pdata(&pdata);

   }
};
