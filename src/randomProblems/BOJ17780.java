package randomProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17780 {
    static int[] dx = {0, 0, 0, -1, 1}; //row
    static int[] dy = {0, 1, -1, 0, 0}; //col
    static int N;
    static int K;
    static List<ChessPiece>[][] board;
    static int[][] boardColor;

    static List<ChessPiece> chessPieces = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int turnCounter = 0;


    public static void main(String[] args) throws IOException {
        initializeGame();
        run();
    }

    private static void initializeGame() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        setBoardColor();
//        for (int[] x : boardColor) {
//            System.out.println(Arrays.toString(x));
//        }
        setChessPiecesList();
//        System.out.println(chessPieces);

        setBoard();
//        for (List<ChessPiece>[] x : board) {
//            System.out.println(Arrays.toString(x));
//        }
    }

    private static void run() {
        while (true) {
//            System.out.println("----------- turn 시작 ------------");
            turnCounter++;
            for (ChessPiece chessPiece : chessPieces) {
                if (chessPiece.carried) continue;
                move(chessPiece);

                if (board[chessPiece.row][chessPiece.col].size() > 3) {
                    System.out.println(turnCounter);
                    return;
                }

                if (turnCounter > 1000) {
                    System.out.println(-1);
                    return;
                }
            }
        }
    }

    private static void move(ChessPiece chessPiece) {
        int[] nextPosition = getNextPosition(chessPiece);
//        System.out.println("이동 전 : " + chessPiece.row + ", " + chessPiece.col);

        if (isNextPositionBlue(nextPosition)) {
            changeDirection(chessPiece);

            nextPosition = getNextPosition(chessPiece);
            if (isNextPositionBlue(nextPosition)) {
//                System.out.println("2회 연속 blue : 이동 안함");
                return;
            }
        }

        List<ChessPiece> tempChessPiece = board[chessPiece.row][chessPiece.col];

//        System.out.println("이동 대상 : " + tempChessPiece);


        if (isNextPositionRed(nextPosition)) {
            tempChessPiece.get(0).carried = true;

            for (int i = tempChessPiece.size()-1; i >= 0; i--) {
                tempChessPiece.get(i).move(tempChessPiece.get(0).direction);
                board[nextPosition[0]][nextPosition[1]].add(tempChessPiece.get(i));
            }
            board[nextPosition[0]][nextPosition[1]].get(0).carried = false;

        } else {
            tempChessPiece.get(0).carried = true;

            for (ChessPiece piece : tempChessPiece) {
                piece.move(tempChessPiece.get(0).direction);
                board[nextPosition[0]][nextPosition[1]].add(piece);
            }

            board[nextPosition[0]][nextPosition[1]].get(0).carried = false;
        }
        tempChessPiece.clear();
//        System.out.println("이동 후 : " + chessPiece.row + ", " + chessPiece.col + " and " + board[nextPosition[0]][nextPosition[1]]);
//        System.out.println("------------------------------------------");
    }

    private static int[] getNextPosition(ChessPiece chessPiece) {
        return new int[] {chessPiece.row + dx[chessPiece.direction], chessPiece.col + dy[chessPiece.direction]};
    }

    private static void changeDirection(ChessPiece chessPiece) {
        chessPiece.direction = chessPiece.direction % 2 == 0 ? chessPiece.direction - 1 : chessPiece.direction + 1;
    }

    private static boolean isNextPositionRed(int[] nextPosition) {
        return boardColor[nextPosition[0]][nextPosition[1]] == 1;
    }

    private static boolean isNextPositionBlue(int[] nextPosition) {
        return nextPosition[0] < 0
                || nextPosition[0] >= N
                || nextPosition[1] < 0
                || nextPosition[1] >= N
                || boardColor[nextPosition[0]][nextPosition[1]] == 2;
    }

    private static void setBoard() {
        board = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for (ChessPiece chessPiece : chessPieces) {
//            System.out.println("chessPiece = " + chessPiece);
//            System.out.println(chessPiece.row + ", "+ chessPiece.col + " - 추가 전 : " + board[chessPiece.row][chessPiece.col]);
            board[chessPiece.row][chessPiece.col].add(chessPiece);
//            System.out.println("추가 후 : " + board[chessPiece.row][chessPiece.col]);
//            System.out.println("-----------------");
        }
    }

    private static void setChessPiecesList() throws IOException {
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            ChessPiece tempChessPiece = new ChessPiece(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            chessPieces.add(tempChessPiece);
        }
    }

    private static void setBoardColor() throws IOException {
        StringTokenizer st;
        boardColor = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                boardColor[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class ChessPiece {
        int row;
        int col;
        int direction;
        boolean carried;

        public ChessPiece(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.carried = false;
        }

        @Override
        public String toString() {
            return "{" +
                    "row=" + row +
                    ", col=" + col +
                    ", carried=" + carried +
                    '}';
        }

        public void move(int direction) {
            this.row += dx[direction];
            this.col += dy[direction];
        }
    }
}

