module.exports = ({
	name: 'wacc',
	rules: {
		source_file: $ => seq(
			'begin',
			repeat($.function_definition),
			repeat($.statement),
			'end'
		),

		function_definition: $ => seq(
			$.type,
			$.identifier,
			'(',
			optional($.param_list),
			')',
			'IS',
			repeat($.statement),
			'END'
		),

		type: $ => choice(
			$.primitive_type,
			$.array_type,
			$.pair_type
		),

		param_list: $ => seq(
			$.param,
			optional(seq(',', $.param_list))
		),

		param: $ => seq(
			$.type,
			$.identifer
		),

		statement: $ => choice(
			'skip',
			$.decleration,
			$.assignment,
			$.read,
			$.free,
			$.return,
			$.exit,
			$.print,
			$.println,
			$.if_statement,
			$.while_statement,
			$.stat_block,
			$.stat_list
		),

		expr: $ => choice(
			$.int_lit,
			$.bool_lit,
			$.char_lit,
			$.str_lit,
			$.pair_lit,
			$.identifier,
			$.array_elem,
			$.unary_op_expr,
			$.binary_op_expr,
			seq('(', $.expr, ')')
		),

		decleration: $ => seq(
			$.type,
			$.identifer,
			'=',
			$.assign_rhs
		),

		assignment: $ => seq(
			$.assign_lhs,
			$.assign_rhs
		),

		read: $ => seq(
			'read',
			$.assign_lhs
		),

		free: $ => seq(
			'free',
			$.expr
		),

		return: $ => seq(
			'return',
			$.expr
		),

		exit: $ => seq(
			'exit',
			$.expr
		),

		print: $ => seq(
			'print',
			$.expr
		),

		println: $ => seq(
			'println',
			$.expr
		),

		if_statement: $ => seq(
			'if',
			$.expr,
			'then',
			$.statement,
			'else',
			$.statement,
			'fi'
		),

		while_statement: $ => seq(
			'while',
			$.expr,
			'do',
			$.statement,
			'done'
		),

		stat_list: $ => seq(
			$.statement,
			',',
			$.statement
		),

		assign_rhs: $ => choice(
			$.expr,
			$.array_lit,
			seq('newpair', '(', $.expr, ',', $.expr, ')'),
			$.pair_elem,
			seq('call', $.identifier, '(', $.arg_list, ')')
		),

		arg_list: $ => optional(seq(
			$.expr,
			repeat(',', $.expr)
		)),

		assign_lhs: $ => choice(
			$.identifer,
			$.array_elem,
			$.pair_elem
		),

		primitive_type: $ => choice(
			'int',
			'bool',
			'char',
			'string'
		),

		array_type: $ => seq(
			$.primitive_type,
			repeat1('[]')
		),

		array_lit: $ => seq(
			'[',
			$.expr,
			repeat(seq(',', $.expr)),
			']',
		),

		pair_type: $ => seq(
			'pair',
			'(',
			$.pair_elem_type,
			',',
			$.pair_elem_type,
			')'
		),

		pair_elem: $ => choice(
			seq('fst', $.expr),
			seq('snd', $.expr)
		),

		pair_elem_type: $ => choice(
			$.primitive_type,
			$.array_type,
			'pair'
		),

		unary_op_expr: $ => prec(2, choice(
			seq('-', $.expr),
			seq('!', $.expr),
			seq('len', $.expr),
			seq('ord', $.expr),
			seq('chr', $.expr)
		)),

		binary_op_expr: $ => choice(
			prec.left(13, seq($.expr, '*', $.expr)),
			prec.left(12, seq($.expr, '/', $.expr)),
			prec.left(11, seq($.expr, '%', $.expr)),
			prec.left(10, seq($.expr, '+', $.expr)),
			prec.left(9, seq($.expr, '-', $.expr)),
			prec.left(8, seq($.expr, '>=', $.expr)),
			prec.left(7, seq($.expr, '<=', $.expr)),
			prec.left(6, seq($.expr, '>', $.expr)),
			prec.left(5, seq($.expr, '<', $.expr)),
			prec.left(4, seq($.expr, '==', $.expr)),
			prec.left(3, seq($.expr, '!=', $.expr)),
			prec.left(2, seq($.expr, '&&', $.expr)),
			prec.left(1, seq($.expr, '||', $.expr)),
		),

		array_elem: $ => seq(
			$.identifer,
			repeat1(seq('[', $.expr, ']'))
		),

		int_lit: $ => /\d+/,

		bool_lit: $ => choice(
			'true',
			'false'
		),

		char_lit: $ => seq(
			'\'',
			/[a-z]/,
			'\''
		),

		str_lit: $ => seq(
			'\'',
			/[a-z]+/,
			'\''
		),

		pair_lit: $ => choice(
			'null',
		),

		identifier: $ => /[a-z]+/

	}
})
