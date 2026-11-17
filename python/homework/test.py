import numpy as np2
import pandas as pd
def test():
        df=pd.DataFrame({
            'a':[1,2,4,3,3,3,4],
            'b':[2,3,3,4,4,5,3]
        })
        print('去重前：\n',df)

        #根据字段a进行去重，保留第一次出现的数据
        df.drop_duplicates(['a'],keep='first',inplace=True)
        print('去重后：\n',df)
if __name__ == '__main__':
    test()