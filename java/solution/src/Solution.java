class Solution {
 public void solveSudoku(char[][] board) {
  if(board == null || board.length != 9 || board[0].length != 9){return;}
  boolean res = SudokuHelper(board,0,0);
 }
 //给 board【row】【col】填数
 public boolean SudokuHelper(char [][] board, int row,int col){

  while(row < 9 && col < 9){
   if(board[row][col] == '.'){break;}
   if(col == 8){
    col = 0;
    row ++;
   }else{
    col ++;
   }
  }
  //没有可填充的了 全部完成
  if(row >= 9){
   return true;
  }

  //找出下一个填充点
  int rowNew = row + col/8;
  int colNew = (col + 1)%9;

  //所以接下来尝试给 row col 填充数字
  for(int i = 1; i <= 9; i++){
   //检查i的可行性
   if(isValid(board,row,col,i)){
    board[row][col] = (char)(i + '0');
    boolean res = SudokuHelper(board,rowNew,colNew);
    if(res){
     //填充成功
     return true;
    }else{
     //填充失败
     board[row][col] = '.';
    }
   }
  }
  return false;
 }
 public boolean isValid(char [][] board, int row,int col,int num){
  for(int i = 0; i < board.length; i++){
   if(board[row][i] == num+'0' || board[i][col] == num + '0'){
    return false;
   }
  }
  int rowOff = row/3*3;
  int colOff = col/3*3;
  for(int i = 0;i < 3;i++){
   for(int j = 0; j < 3;j++){
    if(board[rowOff + i][colOff + j] == num + '0'){
     return false;
    }
   }
  }
  return true;
 }
}